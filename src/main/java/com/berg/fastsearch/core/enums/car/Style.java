package com.berg.fastsearch.core.enums.car;

/**
 * <p>车型</p>
 *
 * @version: v1.0
 * @author: bergturing@qq.com
 * @apiNote: Created on 18-5-13
 */
public enum Style {
    HATCHBACK("HATCHBACK", "两厢轿车"),
    THREE("THREE", "三厢轿车"),
    SPORT("SPORT", "跑车"),
    SUV("SUV", "SUV"),
    MPV("MPV", "MPV"),
    MICROBUS("MICROBUS", "面包车"),
    PICKUP("PICKUP", "皮卡"),
    OTHER("OTHER", "其他")
    ;

    private String code;

    private String name;

    Style(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Style get(String code){
        for (Style style : Style.values()) {
            if(style.getCode().equals(code)){
                return style;
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
