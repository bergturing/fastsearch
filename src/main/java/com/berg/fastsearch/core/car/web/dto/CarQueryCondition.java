package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public class CarQueryCondition extends BaseQueryCondition {
    private static final long serialVersionUID = 4319051233705555484L;

    /**
     * 城市字段名
     */
    public static final String FIELD_CITY_EN_NAME = "cityEnName";

    /**
     * 区域字段名
     */
    public static final String FIELD_REGION_EN_NAME = "regionEnName";

    /**
     * 品牌代码字段名
     */
    public static final String FIELD_BRAND_CODE = "brandCode";

    /**
     * 系列代码字段名
     */
    public static final String FIELD_SERIES_CODE = "seriesCode";

    /**
     * 价格字段名
     */
    public static final String FIELD_PRICE = "price";

    /**
     * 状态字段名
     */
    public static final String FIELD_STATUS = "status";

    public static final String AGG_REGION = "agg_region";

    /**
     * 城市简写英文名
     */
    private String cityEnName;

    /**
     * 地区简写英文名
     */
    private String regionEnName;

    /**
     * 品牌代码
     */
    private String brandCode;

    /**
     * 系列代码
     */
    private String seriesCode;

    /**
     * 价格区间
     */
    private String priceBlock;

    /**
     * 车辆的状态
     */
    private String status;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 左上角
     */
    private Double leftLongitude;
    private Double leftLatitude;

    /**
     * 右下角
     */
    private Double rightLongitude;
    private Double rightLatitude;



    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getRegionEnName() {
        return regionEnName;
    }

    public void setRegionEnName(String regionEnName) {
        this.regionEnName = regionEnName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getPriceBlock() {
        return priceBlock;
    }

    public void setPriceBlock(String priceBlock) {
        this.priceBlock = priceBlock;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Double getLeftLongitude() {
        return leftLongitude;
    }

    public void setLeftLongitude(Double leftLongitude) {
        this.leftLongitude = leftLongitude;
    }

    public Double getLeftLatitude() {
        return leftLatitude;
    }

    public void setLeftLatitude(Double leftLatitude) {
        this.leftLatitude = leftLatitude;
    }

    public Double getRightLongitude() {
        return rightLongitude;
    }

    public void setRightLongitude(Double rightLongitude) {
        this.rightLongitude = rightLongitude;
    }

    public Double getRightLatitude() {
        return rightLatitude;
    }

    public void setRightLatitude(Double rightLatitude) {
        this.rightLatitude = rightLatitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
