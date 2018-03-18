package com.berg.fastsearch.account.mapper;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.util.List;

/**
 * <p>角色mapper测试</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class RoleMapperTest extends FastsearchApplicationTests {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 测试select
     */
    @Test
    public void testSelect(){
        Role role = new Role();
        role.setId(1L);
        List<Role> select = roleMapper.select(role);

        Assert.assertEquals(1L, select.size());
        Assert.assertEquals("ADMIN", select.get(0).getName());
    }

    /**
     * 测试selectByPrimaryKey
     */
    @Test
    public void testSelectByPrimaryKey(){
        Role role = new Role();
        role.setId(1L);

        role = roleMapper.selectByPrimaryKey(role);

        Assert.assertEquals("ADMIN", role.getName());
    }
}
