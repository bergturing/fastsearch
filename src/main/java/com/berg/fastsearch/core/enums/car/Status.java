package com.berg.fastsearch.core.enums.car;

/**
 * <p>车辆的状态</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public enum  Status {
    /**
     * 车辆新建或则还未审核时候的状态
     */
    UNAUDITED("UNAUDITED", "未审核"),
    /**
     * 车辆已经通过审核,用户只能查看已经通过审核的车辆
     */
    PASSED("PASSED", "审核通过"),
    /**
     * 车辆已经出售了
     */
    SOLDED("SOLDED", "已出售"),
    /**
     * 删除数据只是从逻辑上删除数据
     */
    DELETED("DELETED", "逻辑删除");

    private String code;

    private String name;

    public static Status get(String code){
        for (Status style : Status.values()) {
            if(style.getCode().equals(code)){
                return style;
            }
        }

        throw new IllegalArgumentException();
    }

    Status(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
