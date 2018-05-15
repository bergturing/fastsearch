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

    public static final String FIELD_CITY_EN_NAME = "cityEnName";

    public static final String FIELD_REGION_EN_NAME = "regionEnName";

    /**
     * 城市简写英文名
     */
    private String cityEnName;

    /**
     * 地区简写英文名
     */
    private String regionEnName;

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
