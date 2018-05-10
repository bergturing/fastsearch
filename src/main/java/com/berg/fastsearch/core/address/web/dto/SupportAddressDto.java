package com.berg.fastsearch.core.address.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class SupportAddressDto extends BaseDto<Long>{
    private static final long serialVersionUID = -3795339942715588313L;

    // 上一级行政单位
    private String belongTo;


    private String enName;

    private String cnName;

    private String level;

    private double baiduMapLng;

    private double baiduMapLat;

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
}
