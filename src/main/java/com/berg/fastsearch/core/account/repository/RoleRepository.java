package com.berg.fastsearch.core.account.repository;

import com.berg.fastsearch.core.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
