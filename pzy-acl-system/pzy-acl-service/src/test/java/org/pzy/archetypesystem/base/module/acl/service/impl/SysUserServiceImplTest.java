package org.pzy.archetypesystem.base.module.acl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.ApiTestApp;
import org.pzy.archetypesystem.base.module.acl.dto.SysUserAddDTO;
import org.pzy.archetypesystem.base.module.acl.dto.SysUserEditDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.opensource.domain.PageT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void beforeMethod() {
        sysUserService.clearCache();
    }

    @Test
    public void testPageAndCache() {
        PageT<SysUserVO> result = sysUserService.pageAndCache(null);
        assert 0 == result.getTotal();
        assert result.getList().isEmpty();
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
        Long id = 1246960484633153536L;
        String name = "   张三   ";
        SysUserEditDTO dto = new SysUserEditDTO().setId(1246960484633153536L).setName(name);
        this.sysUserService.updateByIdAndClearCache(dto);
        SysUserVO sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null != sysUserVO;
        assert name.trim().equals(sysUserVO.getName());
    }

    @Test
    public void testRemoveByIdAndClearCache() {
        Long id = 1246960484633153536L;
        SysUserVO sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null != sysUserVO;
        this.sysUserService.removeByIdAndClearCache(id);
        sysUserVO = this.sysUserService.getByIdAndCache(id);
        assert null == sysUserVO;
    }
}