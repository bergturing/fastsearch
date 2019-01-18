package com.berg.fastsearch.core.enums.car;

import javax.swing.plaf.metal.OceanTheme;

/**
 * <p>燃油类型</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-13
 */
public enum FuelType {
    GASOLINE("GASOLINE", "汽油"),
    DIESEL_OIL("DIESEL_OIL", "柴油"),
    ELECTRIC_POWER("ELECTRIC_POWER", "电动"),
    MIX("MIX", "油电混合"),
    OTHER("OTHER", "其他")
    ;

    private String code;

    private String name;

    FuelType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FuelType get(String code){
        for (FuelType fuelType : FuelType.values()) {
            if(fuelType.getCode().equals(code)){
                return fuelType;
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
