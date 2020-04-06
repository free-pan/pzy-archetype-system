package org.pzy.archetypesystem.base.module.acl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.ApiTestApp;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.domain.PageT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.TimeUnit;

/**
 * SysUserServiceImplTest
 *
 * @author pan
 * @date 2020/4/6 07:08
 */
@SpringBootTest(classes = ApiTestApp.class)
@Slf4j
public class SysUserServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testPageAndCache() {
        PageT<SysUserVO> result = sysUserService.pageAndCache(null);
        assert 0 == result.getTotal();
        assert result.getList().isEmpty();
    }

    @Test
    public void testPageAndCache01() {
        PageT<SysUserVO> result = sysUserService.pageAndCache(new SysUserSearchDTO());
        assert 0 != result.getTotal();
        assert !result.getList().isEmpty();
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto: 不能为null")
    public void testSaveAndClearCache() {
        sysUserService.saveAndClearCache(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入邮箱!, saveAndClearCache.dto.name: 请输入姓名!")
    public void testSaveAndClearCache01() {
        sysUserService.saveAndClearCache(new SysUserAddDTO());
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入邮箱!")
    public void testSaveAndClearCache02() {
        SysUserAddDTO dto = new SysUserAddDTO();
        dto.setName("张三");
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.name: 请输入200字符以内的姓名!, saveAndClearCache.dto.email: 请输入邮箱!")
    public void testSaveAndClearCache03() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "";
        for (int i = 0; i < 201; i++) {
            name += "a";
        }
        dto.setName(name);
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入邮箱!, saveAndClearCache.dto.name: 请输入200字符以内的姓名!")
    public void testSaveAndClearCache04() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = " ";
        for (int i = 0; i < 200; i++) {
            name += "a";
        }
        dto.setName(name);
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入邮箱!, saveAndClearCache.dto.name: 请输入200字符以内的姓名!")
    public void testSaveAndClearCache05() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "";
        for (int i = 0; i < 200; i++) {
            name += "a";
        }
        name += " ";
        dto.setName(name);
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入邮箱!")
    public void testSaveAndClearCache06() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "";
        for (int i = 0; i < 200; i++) {
            name += "a";
        }
        dto.setName(name);
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入正确的邮箱地址!")
    public void testSaveAndClearCache07() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "zs";
        dto.setName(name);
        dto.setEmail("test");
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入正确的邮箱地址!")
    public void testSaveAndClearCache08() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "zs";
        dto.setName(name);
        dto.setEmail("test@");
        sysUserService.saveAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "saveAndClearCache.dto.email: 请输入正确的邮箱地址!")
    public void testSaveAndClearCache09() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "zs";
        dto.setName(name);
        dto.setEmail("@test");
        sysUserService.saveAndClearCache(dto);
    }

    @Test
    public void testSaveAndClearCache10() {
        SysUserAddDTO dto = new SysUserAddDTO();
        String name = "zs";
        dto.setName(name);
        dto.setEmail("sd4015700@126.com");
        sysUserService.saveAndClearCache(dto);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("主进程结束!");
    }

    @Test
    public void testGetByIdAndCache() {
        assert null == this.sysUserService.getByIdAndCache(null);
    }

    @Test
    public void testGetByIdAndCache01() {
        assert null != this.sysUserService.getByIdAndCache(1246960484633153536L);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "updateByIdAndClearCache.dto: 不能为null")
    public void testUpdateByIdAndClearCache() {
        this.sysUserService.updateByIdAndClearCache(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "updateByIdAndClearCache.dto.name: 请输入姓名!, updateByIdAndClearCache.dto.id: 不能为null")
    public void testUpdateByIdAndClearCache01() {
        SysUserEditDTO dto = new SysUserEditDTO();
        this.sysUserService.updateByIdAndClearCache(dto);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "updateByIdAndClearCache.dto.name: 请输入姓名!")
    public void testUpdateByIdAndClearCache02() {
        SysUserEditDTO dto = new SysUserEditDTO().setId(1246960484633153536L);
        this.sysUserService.updateByIdAndClearCache(dto);
    }

    @Test
    public void testUpdateByIdAndClearCache03() {
        Long id = 1246997930569764864L;
        String name = "   张三   ";
        SysUserEditDTO dto = new SysUserEditDTO().setId(id).setName(name);
        this.sysUserService.updateByIdAndClearCache(dto);
        SysUserVO sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null != sysUserVO;
        assert name.trim().equals(sysUserVO.getName());
    }

    @Test
    public void testRemoveByIdAndClearCache() {
        Long id = 1246997930569764864L;
        SysUserVO sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null != sysUserVO;
        this.sysUserService.removeByIdAndClearCache(id);
        sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null == sysUserVO;
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "sendActiveEmailAgain.email: 不能为null")
    public void testSendActiveEmailAgain() {
        this.sysUserService.sendActiveEmailAgain(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "sendActiveEmailAgain.email: 不是一个合法的电子邮件地址")
    public void testSendActiveEmailAgain02() {
        this.sysUserService.sendActiveEmailAgain("a");
    }

    @Test(expectedExceptions = ValidateException.class, expectedExceptionsMessageRegExp = "无效的邮箱地址!")
    public void testSendActiveEmailAgain03() {
        this.sysUserService.sendActiveEmailAgain("test@126.com");
    }

    @Test
    public void testSendActiveEmailAgain04() {
        this.sysUserService.sendActiveEmailAgain("sd4015700@126.com");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "activeAccountAndClearCache.validateCount: 不能为空")
    public void testActiveAccountAndClearCache() {
        this.sysUserService.activeAccountAndClearCache(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "activeAccountAndClearCache.validateCount: 不能为空")
    public void testActiveAccountAndClearCache01() {
        this.sysUserService.activeAccountAndClearCache("");
    }

    @Test
    public void testActiveAccountAndClearCache02() {
        this.sysUserService.activeAccountAndClearCache("a");
    }

    @Test
    public void testActiveAccountAndClearCache03() {
        System.out.println(this.sysUserService.activeAccountAndClearCache("7f19c844da4e4a64a141c970517ea647"));
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "modifyPasswordAndClearCache.dto: 不能为null")
    public void testModifyPasswordAndClearCache() {
        ModifyPasswordDTO modifyPasswordDTO = null;
        this.sysUserService.modifyPasswordAndClearCache(modifyPasswordDTO);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "modifyPasswordAndClearCache.dto.confirmPwd: 请输入确认密码!, modifyPasswordAndClearCache.dto.oldPwd: 请输入原始密码!, modifyPasswordAndClearCache.dto.id: 不能为null, modifyPasswordAndClearCache.dto.newPwd: 请输入新密码!")
    public void testModifyPasswordAndClearCache01() {
        ModifyPasswordDTO modifyPasswordDTO = new ModifyPasswordDTO();
        this.sysUserService.modifyPasswordAndClearCache(modifyPasswordDTO);
    }

    @Test(expectedExceptions = ValidateException.class, expectedExceptionsMessageRegExp = "确认密码与新密码不一致!")
    public void testModifyPasswordAndClearCache02() {
        ModifyPasswordDTO modifyPasswordDTO = new ModifyPasswordDTO();
        modifyPasswordDTO.setId(1246997930569764864L);
        String newPwd = "test123";
        modifyPasswordDTO.setNewPwd(newPwd);
        modifyPasswordDTO.setConfirmPwd("123456");
        modifyPasswordDTO.setOldPwd("good123");
        this.sysUserService.modifyPasswordAndClearCache(modifyPasswordDTO);
    }

    @Test(expectedExceptions = ValidateException.class, expectedExceptionsMessageRegExp = "确认密码与新密码不一致!")
    public void testModifyPasswordAndClearCache03() {
        ModifyPasswordDTO modifyPasswordDTO = new ModifyPasswordDTO();
        modifyPasswordDTO.setId(1246997930569764864L);
        String newPwd = "test123";
        modifyPasswordDTO.setNewPwd(newPwd);
        modifyPasswordDTO.setConfirmPwd("123456");
        modifyPasswordDTO.setOldPwd("good123");
        this.sysUserService.modifyPasswordAndClearCache(modifyPasswordDTO);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "sendModifyPasswordValidCode.email: 不能为空")
    public void testSendModifyPasswordValidCode() {
        this.sysUserService.sendResetPasswordValidCode(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "sendModifyPasswordValidCode.email: 不能为空, sendModifyPasswordValidCode.email: 不是一个合法的电子邮件地址")
    public void testSendModifyPasswordValidCode01() {
        this.sysUserService.sendResetPasswordValidCode(" ");
    }

    @Test
    public void testSendModifyPasswordValidCode02() {
        this.sysUserService.sendResetPasswordValidCode("sd4015700@126.com");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class, expectedExceptionsMessageRegExp = "resetPasswordAndClearCache.dto: 不能为null")
    public void testResetPasswordAndClearCache() {
        this.sysUserService.resetPasswordAndClearCache(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testResetPasswordAndClearCache01() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        this.sysUserService.resetPasswordAndClearCache(dto);
    }

    @Test
    public void testResetPasswordAndClearCache02() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setEmail("sd4015700@126.com");
        this.sysUserService.resetPasswordAndClearCache(dto);
    }

    @Test
    public void testResetPasswordAndClearCache03() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setEmail("sd4015700@126.com");
        dto.setVerifyCode("y83h7n");
        String pwd = "good124";
        this.sysUserService.resetPasswordAndClearCache(dto);
    }

    @Test
    public void testResetPasswordAndClearCache04() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setEmail("sd4015700@126.com");
        dto.setVerifyCode("y83h7n");
        String pwd = "good124";
        dto.setNewPwd(pwd);
        this.sysUserService.resetPasswordAndClearCache(dto);
    }

    @Test
    public void testResetPasswordAndClearCache05() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setEmail("sd4015700@126.com");
        dto.setVerifyCode("y83h7n");
        String pwd = "good124";
        dto.setNewPwd(pwd);
        dto.setConfirmPwd("hello11");
        this.sysUserService.resetPasswordAndClearCache(dto);
    }

    @Test
    public void testResetPasswordAndClearCache06() {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setEmail("sd4015700@126.com");
        dto.setVerifyCode("y83h7n");
        String pwd = "good124";
        dto.setNewPwd(pwd);
        dto.setConfirmPwd(pwd);
        this.sysUserService.resetPasswordAndClearCache(dto);
    }
}