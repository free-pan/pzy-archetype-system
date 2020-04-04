package org.pzy.archetypesystem.acl.sysuser.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.*;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.TimeUnit;

/**
 * @author pan
 * @date 2020/3/25
 */

@SpringBootTest(classes = AclTestApp.class)
@Slf4j
public class SysUserServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testAdd01() {
        try {
            sysUserService.addClearCache(null);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            log.error("发生异常", e);
            throw e;
        }
    }

    @Test
    public void testAdd02() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setEmail("123@126.com");
        sysUserAddDTO.setName("张三");
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        try {
            id = sysUserService.addClearCache(sysUserAddDTO);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            log.error("发生异常", e);
            throw e;
        }
    }

    @Test
    public void testAdd03() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setName("张三");
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        try {
            id = sysUserService.addClearCache(sysUserAddDTO);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            log.error("发生异常", e);
            throw e;
        }
    }

    @Test
    public void testAdd04() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setEmail("sd4015700@126.com");
        sysUserAddDTO.setName("潘志勇");
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        id = sysUserService.addClearCache(sysUserAddDTO);
        System.err.println("主业务执行完毕...");
        try {
            TimeUnit.SECONDS.sleep(15);
            System.err.println("主线程完毕...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd05() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setEmail("abc");
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        try {
            id = sysUserService.addClearCache(sysUserAddDTO);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            log.error("发生异常", e);
            throw e;
        }
    }

    @Test
    public void testEdit() {
        System.out.println(sysUserService.searchSimpleUserByIdAndCache(1243552216950902784L));
    }

    @Test
    public void test(){
        System.out.println(sysUserService.searchPageAndCache(null));
    }
}