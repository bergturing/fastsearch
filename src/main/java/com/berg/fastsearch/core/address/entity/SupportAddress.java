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
    private Long belongTo;

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
}
