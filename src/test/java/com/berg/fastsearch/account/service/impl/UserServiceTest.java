package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.account.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

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

}
