package com.berg.fastsearch.core.system.code.repository;

import com.berg.fastsearch.core.system.code.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface CodeRepository extends JpaRepository<Code, Long> {
}
