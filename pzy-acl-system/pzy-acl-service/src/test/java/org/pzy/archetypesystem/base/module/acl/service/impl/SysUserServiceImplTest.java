package org.pzy.archetypesystem.base.module.acl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.AclTestApp;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * SysUserServiceImplTest
 *
 * @author pan
 * @date 2020/4/6 07:08
 */
@SpringBootTest(classes = AclTestApp.class)
@Slf4j
public class SysUserServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testPageAndCache() {
        sysUserService.pageAndCache(null);
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