package com.berg.fastsearch.core.car.web.dto;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-18
 */
public class CarBucketDto {
    /**
     * 聚合bucket的key
     */
    private String key;

    /**
     * 聚合结果值
     */
    private long count;

    public CarBucketDto(String key, long count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
