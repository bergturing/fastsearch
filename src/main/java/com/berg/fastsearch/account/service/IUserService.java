package com.berg.fastsearch.account.service;

import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.system.service.IBaseService;

/**
 * <p>用户服务接口</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public interface IUserService extends IBaseService<User>{
    /**
     * 通过用户名来查找用户
     * @param userName  用户名
     * @return          找到的用户对象
     */
    User findUsserByName(String userName);
}
