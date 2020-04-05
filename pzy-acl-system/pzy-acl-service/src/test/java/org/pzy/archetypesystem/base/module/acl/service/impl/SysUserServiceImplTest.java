package org.pzy.archetypesystem.base.module.acl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.ApiTestApp;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.opensource.domain.PageT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test
    public void testSaveAndClearCache() {
    }

    @Test
    public void testGetByIdAndCache() {
    }

    @Test
    public void testUpdateByIdAndClearCache() {
    }

    @Test
    public void testRemoveByIdAndClearCache() {
    }
}