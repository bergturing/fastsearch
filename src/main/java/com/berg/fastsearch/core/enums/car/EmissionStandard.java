package com.berg.fastsearch.core.enums.car;

/**
 * <p>汽车排放标准</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-13
 */
public enum EmissionStandard {
    LEVEL_TWO("LEVEL_TWO", "国二"),
    LEVEL_THREE("LEVEL_THREE", "国三"),
    LEVEL_FOUR("LEVEL_FOUR", "国四"),
    LEVEL_FIVE("LEVEL_FIVE", "国五"),
    OTHER("OTHER", "其他")
    ;

    private String code;

    private String name;

    EmissionStandard(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EmissionStandard get(String code){
        for (EmissionStandard emissionStandard : EmissionStandard.values()) {
            if (emissionStandard.getCode().equals(code)){
                return emissionStandard;
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
