package com.berg.fastsearch.core.system.kafka.service.impl;

import com.berg.fastsearch.core.system.kafka.message.BaseIndexMessage;
import com.berg.fastsearch.core.system.kafka.message.DefaultMessage;
import com.berg.fastsearch.core.system.kafka.service.IKafkaService;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
@Service
public class KafkaServiceImpl implements IKafkaService {

    private static final String INDEX_TOPIC = "fastsearch";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 自动注入所有的ISearchService接口的实现对象
     */
    @Autowired
    private Map<String, ISearchService> searchServiceMap;

    @Override
    public void send(BaseIndexMessage message) throws JsonProcessingException {
        kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
    }

    @KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(String content) throws IOException {
        //解析消息
        BaseIndexMessage message = objectMapper.readValue(content, DefaultMessage.class);

        //获取服务对象
        ISearchService searchService = searchServiceMap.get(message.getProcessServiceName());

        searchService.processMessage(content);

    }
}
