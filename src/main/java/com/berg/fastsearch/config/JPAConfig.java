package com.berg.fastsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-1
 */
@Configuration
//@EnableJpaRepositories(basePackage = "com.berg.fastsearch.repository")
@EnableTransactionManagement
public class JPAConfig {
}
