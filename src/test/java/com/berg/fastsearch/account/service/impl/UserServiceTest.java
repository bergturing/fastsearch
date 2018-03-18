package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.account.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>用户服务测试</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class UserServiceTest extends FastsearchApplicationTests {
    @Autowired
    private IUserService userService;

    /**
     * 测试select
     */
    @Test
    public void testSelect(){
        User user = new User();
        user.setId(1L);
        List<User> select = userService.select(user);

        Assert.assertEquals(1L, select.size());
        Assert.assertEquals("admin1", select.get(0).getName());
    }
}
