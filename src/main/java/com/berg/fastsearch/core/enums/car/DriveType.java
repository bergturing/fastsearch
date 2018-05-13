package com.berg.fastsearch.core.enums.car;

/**
 * <p>驱动类型</p>
 *
 * @version: v1.0
 * @author: bo.he02@hand-china.com
 * @apiNote: Created on 18-5-13
 */
public enum DriveType {
    TWO_DRIVER("TWO_DRIVER", "两驱"),
    FOUR_DRIVER("FOUR_DRIVER", "四驱"),
    OTHER("OTHER", "其他")
    ;

    private String code;

    private String name;

    DriveType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DriveType get(String code){
        for (DriveType driveType : DriveType.values()) {
            if(driveType.getCode().equals(code)){
                return driveType;
            }
        }

        return OTHER;
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
