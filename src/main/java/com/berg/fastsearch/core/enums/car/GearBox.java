package com.berg.fastsearch.core.enums.car;

/**
 * <p>变速箱类型</p>
 *
 * @version: v1.0
 * @author: bo.he02@hand-china.com
 * @apiNote: Created on 18-5-13
 */
public enum GearBox {
    HAND("HAND", "手动"),
    AUTO("AUTO", "自动"),
    OTHER("OTHER", "其他")
    ;

    private String code;

    private String name;

    GearBox(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GearBox get(String code){
        for (GearBox gearBox : GearBox.values()) {
            if (gearBox.getCode().equals(code)){
                return gearBox;
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
