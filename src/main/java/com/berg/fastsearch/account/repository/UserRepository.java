package com.berg.fastsearch.account.repository;

import com.berg.fastsearch.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名来查找用户
     * @param name      用户名
     * @return          找到的用户
     */
    User findByName(String name);
}
