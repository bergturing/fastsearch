package com.berg.fastsearch.core.enums.car;

/**
 * <p>车辆颜色</p>
 *
 * @version: v1.0
 * @author: bergturing@qq.com
 * @apiNote: Created on 18-5-13
 */
public enum Color {
    BLACK("BLACK", "黑色", "#000000"),
    WHITE("WHITE", "白色", "#ffffff"),
    SILVER_GRAY("SILVER_GRAY", "银灰色", ""),
    DARK_GRAY("DARK_GRAY", "深灰色", ""),
    RED("RED", "红色", ""),
    GREEN("GREEN", "绿色", ""),
    ORANGE("ORANGE", "橙色", ""),
    BLUE("BLUE", "蓝色", ""),
    YELLOW("YELLOW", "黄色", ""),
    COFFEE("COFFEE", "咖啡色", ""),
    NATURE("NATURE", "香槟色", ""),
    OTHER("OTHER", "其他", "")
    ;

    private String code;

    private String name;

    private String hex;

    Color(String code, String name, String hex) {
        this.code = code;
        this.name = name;
        this.hex = hex;
    }

    public static Color get(String code){
        for (Color color : Color.values()) {
            if(color.getCode().equals(code)){
                return color;
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

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
