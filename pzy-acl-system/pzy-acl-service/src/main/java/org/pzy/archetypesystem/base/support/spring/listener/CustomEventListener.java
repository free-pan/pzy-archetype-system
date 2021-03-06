package org.pzy.archetypesystem.base.support.spring.listener;//package org.pzy.archetypesystem.acl.support.spring.listener;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.acl.entity.SysUser;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.support.spring.event.ChangePasswordSendValidateCodeEvent;
import org.pzy.archetypesystem.base.support.spring.event.UserAddEvent;
import org.pzy.opensource.comm.util.RandomPasswordUtil;
import org.pzy.opensource.email.domain.bo.EmailMessageBO;
import org.pzy.opensource.email.domain.bo.EmailServerPropertiesBO;
import org.pzy.opensource.email.support.util.EmailUtil;
import org.pzy.opensource.redis.support.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 自定义事件监听
 *
 * @author pan
 * @date 2020/3/27
 */
@Component
@Slf4j
public class CustomEventListener {

    /**
     * 邮箱服务器地址. 如: smtp.163.com
     */
    @Value("${email.mailServerHost}")
    private String mailServerHost;
    /**
     * 发件人邮箱地址. 如: test@163.com
     */
    @Value("${email.mailSenderAddress}")
    private String mailSenderAddress;
    /**
     * 发件人邮箱账号. 如: test@163.com
     */
    @Value("${email.mailSenderUsername}")
    private String mailSenderUsername;
    /**
     * 发件人邮箱的登录密码. 如: 123456
     */
    @Value("${email.password}")
    private String password;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据邮箱构建密码修改验证码存储key
     *
     * @param email
     * @return
     */
    public static final String buildPasswordModifyVerifyCodeRedisKey(String email) {
        return email;
    }

    @EventListener
    @Async
    public void onUserAddEvent(UserAddEvent event) {
        log.debug("监听到用户新增事件:{}", event.getUserId());
        SysUser sysUser = sysUserService.getById(event.getUserId());
        if (null == sysUser) {
            log.error("用户信息不存在! id={}", event.getUserId());
        } else {
            String uuid = UUID.fastUUID().toString(true);
            String key = uuid;
            EmailMessageBO emailMessageBO = new EmailMessageBO();
            emailMessageBO.setTitle("账号激活");
            emailMessageBO.setToAddr(Arrays.asList(sysUser.getEmail()));
            emailMessageBO.setContent("你好" + sysUser.getName() + "请使用该激活码激活(有效时间1分钟):" + uuid);
            sendEmail(emailMessageBO);
            // redis中存放存放uuid,有效时间为1分钟
            RedisUtil.put(key, event.getUserId(), TimeUnit.MINUTES.toSeconds(1));
            if (log.isDebugEnabled()) {
                log.debug("给邮箱[" + sysUser.getEmail() + "]发送邮件成功!激活码(1分钟内有效):" + uuid);
            }
        }
    }

    /**
     * 发送邮件
     *
     * @param emailMessageBO
     */
    private void sendEmail(EmailMessageBO emailMessageBO) {
        EmailServerPropertiesBO emailServerProperties = new EmailServerPropertiesBO();
        emailServerProperties.setPassword(password);
        emailServerProperties.setMailSenderAddress(mailSenderAddress);
        emailServerProperties.setMailSenderUsername(mailSenderUsername);
        emailServerProperties.setMailServerHost(mailServerHost);
        EmailUtil.send(emailServerProperties, emailMessageBO);
    }

    @EventListener
    @Async
    public void onChangePasswordValidateCodeEvent(ChangePasswordSendValidateCodeEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("发送密码修改验证码事件:{}", event.getEmail());
        }
        // 存储key
        String key = buildPasswordModifyVerifyCodeRedisKey(event.getEmail());
        // 生成验证码
        String verifyCode = (String) RedisUtil.get(key);
        boolean hasOldVerifyCode = true;
        if (StringUtils.isEmpty(verifyCode)) {
            hasOldVerifyCode = false;
            verifyCode = RandomPasswordUtil.generateSixRandomNumberWordVerifyCode();
        }
        EmailMessageBO emailMessageBO = new EmailMessageBO();
        emailMessageBO.setTitle("密码修改验证码");
        emailMessageBO.setToAddr(Arrays.asList(event.getEmail()));
        emailMessageBO.setContent("秒修改验证码(有效时间1分钟):" + verifyCode);
        sendEmail(emailMessageBO);
        if (!hasOldVerifyCode) {
            // 验证码有效时间1分钟
            RedisUtil.put(key, verifyCode, TimeUnit.MINUTES.toSeconds(1));
        }
        if (log.isDebugEnabled()) {
            log.debug("发送给[{}]的密码修改验证码为[{}]", event.getEmail(), verifyCode);
        }
    }
}
