package com.berg.fastsearch.account.mapper;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>用户角色mapper测试</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class UserRoleMapperTest extends FastsearchApplicationTests {
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 测试select
     */
    @Test
    public void testSelect(){
        UserRole userRole = new UserRole();
        userRole.setUserId(1L);
        userRole.setRoleId(1L);

        List<UserRole> select = userRoleMapper.select(userRole);

        Assert.assertEquals(1L, select.size());
    }
}
