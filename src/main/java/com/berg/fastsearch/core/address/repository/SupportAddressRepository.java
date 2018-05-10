package com.berg.fastsearch.core.address.repository;

import com.berg.fastsearch.core.address.entity.SupportAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public interface SupportAddressRepository extends JpaRepository<SupportAddress, Long> {

    /**
     *
     * @param level
     * @param belongTo
     * @return
     */
    List<SupportAddress> findAllByLevelAndBelongTo(String level, String belongTo);

    /**
     *
     * @param enName
     * @param level
     * @return
     */
    SupportAddress findByEnNameAndLevel(String enName, String level);

    /**
     *
     * @param enName
     * @param belongTo
     * @return
     */
    SupportAddress findByEnNameAndBelongTo(String enName, String belongTo);
}
