package com.berg.fastsearch.core.address.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Entity
@Table(name = "fs_support_address")
public class SupportAddress extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上一级行政单位
     */
    @Column
    private Long belongTo;

    /**
     * 地点英文名
     */
    @Column
    private String enName;

    /**
     * 地点中文名
     */
    @Column
    private String cnName;

    /**
     * 行政级别
     */
    @Column
    private String level;

    /**
     * 百度地图经度
     */
    @Column
    private BigDecimal baiduMapLng;

    /**
     * 百度地图纬度
     */
    @Column
    private BigDecimal baiduMapLat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Long belongTo) {
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

    public BigDecimal getBaiduMapLng() {
        return baiduMapLng;
    }

    public void setBaiduMapLng(BigDecimal baiduMapLng) {
        this.baiduMapLng = baiduMapLng;
    }

    public BigDecimal getBaiduMapLat() {
        return baiduMapLat;
    }

    public void setBaiduMapLat(BigDecimal baiduMapLat) {
        this.baiduMapLat = baiduMapLat;
    }
}
