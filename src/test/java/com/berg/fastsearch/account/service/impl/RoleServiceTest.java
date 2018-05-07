package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.account.service.IRoleService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * <p>角色服务测试</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class RoleServiceTest extends FastsearchApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 测试select
     */
    @Test
    public void testSelect(){
        List<RoleDto> select = roleService.findByUserId(1L);

        Assert.assertEquals(1L, select.size());
        Assert.assertEquals("ADMIN", select.get(0).getName());
    }

    @Test
    public void testPass(){
        for (int i = 0; i < 10; i++) {
            logger.info("123456 --> " + passwordEncoder.encode("123456"));
        }
    }
}

