package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.UserRole;
import com.berg.fastsearch.account.service.IUserRoleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>用户角色服务测试</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class UserRoleServiceTest extends FastsearchApplicationTests {
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 测试select
     */
    @Test
    public void testSelect(){
        UserRole userRole = new UserRole();
        userRole.setUserId(1L);
        userRole.setRoleId(1L);

        List<UserRole> select = userRoleService.select(userRole);

        Assert.assertEquals(1L, select.size());
    }
}
