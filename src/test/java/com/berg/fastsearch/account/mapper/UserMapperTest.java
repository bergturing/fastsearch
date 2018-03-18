package com.berg.fastsearch.account.mapper;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class UserMapperTest extends FastsearchApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        User user = new User();
        user.setId(1L);
        List<User> select = userMapper.select(user);

        Assert.assertEquals(1L, select.size());
        Assert.assertEquals("admin1", select.get(0).getName());
    }
}
