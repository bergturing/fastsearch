package com.berg.fastsearch.user.car.dto;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class ValueBlock {
    /**
     * 价格区间定义
     */
    public static final Map<String, ValueBlock> PRICE_BLOCK;


    /**
     * 无限制区间
     */
    public static final ValueBlock ALL = new ValueBlock("*", -1, -1);

    static {
        PRICE_BLOCK = ImmutableMap.<String, ValueBlock>builder()
                .put("*-1000", new ValueBlock("*-1000", -1, 1000))
                .put("1000-3000", new ValueBlock("1000-3000", 1000, 3000))
                .put("3000-*", new ValueBlock("3000-*", 3000, -1))
                .build();
    }

    private String key;
    private int min;
    private int max;

    public ValueBlock(String key, int min, int max) {
        this.key = key;
        this.min = min;
        this.max = max;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static ValueBlock matchPrice(String key) {
        ValueBlock block = PRICE_BLOCK.get(key);
        if (block == null) {
            return ALL;
        }
        return block;
    }

}
