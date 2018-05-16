package com.berg.fastsearch.core.address.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class SupportAddressDto extends BaseDto<Long>{
    private static final long serialVersionUID = -3795339942715588313L;

    /**
     * 上一级行政单位
     */
    private Long belongTo;

    /**
     * 地点英文名
     */
    private String enName;

    /**
     * 地点中文名
     */
    private String cnName;

    /**
     * 行政级别
     */
    private String level;

    /**
     * 百度地图经度
     */
    private BigDecimal baiduMapLng;

    /**
     * 百度地图纬度
     */
    private BigDecimal baiduMapLat;

    /**
     * 上一行政对象的中文名
     */
    private String belongToCnName;

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

    public String getBelongToCnName() {
        return belongToCnName;
    }

    public void setBelongToCnName(String belongToCnName) {
        this.belongToCnName = belongToCnName;
    }
}
