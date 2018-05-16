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
     * 关键词
     */
    private String keywords;

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
}
