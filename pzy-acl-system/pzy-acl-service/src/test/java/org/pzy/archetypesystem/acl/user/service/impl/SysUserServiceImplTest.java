package org.pzy.archetypesystem.acl.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.AclTestApp;
import org.pzy.archetypesystem.acl.user.domain.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;

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
            sysUserService.add(null);
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            log.error("发生异常",e);
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
            id = sysUserService.add(sysUserAddDTO);
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            log.error("发生异常",e);
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
            id = sysUserService.add(sysUserAddDTO);
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            log.error("发生异常",e);
            throw e;
        }
    }

    @Test
    public void testAdd04() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        try {
            id = sysUserService.add(sysUserAddDTO);
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            log.error("发生异常",e);
            throw e;
        }
    }

    @Test
    public void testAdd05() {
        SysUserAddDTO sysUserAddDTO = new SysUserAddDTO();
        sysUserAddDTO.setEmail("abc");
        sysUserAddDTO.setRemark("备注信息!");
        Long id = null;
        try {
            id = sysUserService.add(sysUserAddDTO);
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            log.error("发生异常",e);
            throw e;
        }
    }

    @Test
    public void testEdit() {
    }
}