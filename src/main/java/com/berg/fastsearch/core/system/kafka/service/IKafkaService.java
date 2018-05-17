package com.berg.fastsearch.core.system.kafka.service;

import com.berg.fastsearch.core.system.kafka.message.BaseIndexMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public interface IKafkaService {

    void send(BaseIndexMessage message) throws JsonProcessingException;
}
