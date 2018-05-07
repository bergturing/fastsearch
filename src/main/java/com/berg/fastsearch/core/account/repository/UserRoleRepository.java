package com.berg.fastsearch.core.account.repository;

import com.berg.fastsearch.core.account.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * 根据用户的主键来查找用户的角色
     * @param userId    用户的主键
     * @return          用户的角色
     */
    List<UserRole> findByUserId(Long userId);
}
