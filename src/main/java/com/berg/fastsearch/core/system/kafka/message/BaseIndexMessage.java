package com.berg.fastsearch.core.system.kafka.message;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public abstract class BaseIndexMessage<ID extends Serializable> implements Serializable{
    private static final long serialVersionUID = -4886731026065822810L;

    /**
     * 索引消息
     */
    public static final String INDEX = "index";

    /**
     * 移除消息
     */
    public static final String REMOVE = "remove";

    /**
     * 最大尝试次数
     */
    public static final Integer MAX_RETRY = 3;

    /**
     * 业务主键
     */
    private ID id;

    /**
     * 操作类型   index 或则 remove
     */
    private String operation;

    /**
     * 已经尝试的次数
     */
    private Integer retry = 0;

    /**
     * 处理该条信息的服务对象名称
     */
    private String processServiceName;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public String getProcessServiceName() {
        return processServiceName;
    }

    public void setProcessServiceName(String processServiceName) {
        this.processServiceName = processServiceName;
    }
}
