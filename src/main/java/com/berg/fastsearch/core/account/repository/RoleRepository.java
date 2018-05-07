package com.berg.fastsearch.core.account.repository;

import com.berg.fastsearch.core.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
