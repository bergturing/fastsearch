package com.berg.fastsearch.core.address.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Entity
@Table(name = "fs_support_address")
public class SupportAddress extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 上一级行政单位
    @Column
    private String belongTo;

    @Column
    private String enName;

    @Column
    private String cnName;

    @Column
    private String level;

    @Column
    private double baiduMapLng;

    @Column
    private double baiduMapLat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getBaiduMapLng() {
        return baiduMapLng;
    }

    public void setBaiduMapLng(double baiduMapLng) {
        this.baiduMapLng = baiduMapLng;
    }

    public double getBaiduMapLat() {
        return baiduMapLat;
    }

    public void setBaiduMapLat(double baiduMapLat) {
        this.baiduMapLat = baiduMapLat;
    }

    /**
     * 行政级别定义
     */
    public enum Level {
        CITY("city"),
        REGION("region");

        private String value;

        Level(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Level of(String value) {
            for (Level level : Level.values()) {
                if (level.getValue().equals(value)) {
                    return level;
                }
            }

            throw new IllegalArgumentException();
        }
    }
}
