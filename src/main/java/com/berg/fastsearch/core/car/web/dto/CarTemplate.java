package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.search.entity.BaiduMapLocation;
import com.berg.fastsearch.core.system.search.template.BaseTemplate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public class CarTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = -2006856321242209473L;

    /**
     * 车辆的品牌主键
     */
    private Long brandId;

    /**
     * 车辆品牌代码
     */
    private String brandCode;

    /**
     * 车辆的系列主键
     */
    private Long seriesId;

    /**
     * 车辆系列代码
     */
    private String seriesCode;

    /**
     * 发布者的id,用户表(sys_users)的主键
     */
    private Long deployeeId;

    /**
     * 汽车的标题
     */
    private String title;

    /**
     * 汽车的价格
     */
    private BigDecimal price;

    /**
     * 汽车的座位数
     */
    private Integer seats;

    /**
     * 汽车的排量
     */
    private BigDecimal displacement;

    /**
     * 汽车的里程
     */
    private BigDecimal mileage;

    /**
     * 车龄
     */
    private Integer age;

    /**
     * 汽车变速箱类型,代码维护code:FS.CAR_GEAR_BOXS
     */
    private String gearBox;

    /**
     * 汽车的颜色,代码维护code:FS.CAR_COLORS
     */
    private String color;

    /**
     * 汽车的驱动类型,代码维护code:FS_CAR_DRIVE_TYPES
     */
    private String driveType;

    /**
     * 汽车的排放标准,代码维护code:FS_CAR_DMISSION_STANDARDS
     */
    private String emissionStandard;

    /**
     * 车型,代码维护code:FS_CAR_STYLES
     */
    private String style;

    /**
     * 燃油类型,代码维护code:FS_CAR_FUEL_TYPES
     */
    private String fuelType;

    /**
     * 被看次数
     */
    private Long watchTimes;

    /**
     * 城市主键
     */
    private Long cityId;

    /**
     * 地区主键
     */
    private Long regionId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 封面
     */
    private String cover;

    /**
     * 汽车的状态
     */
    private String status;

    /**
     * 描述
     */
    private String description;

    /**
     * 上次更新记录时间
     */
    private Date lastUpdateTime;

    /**
     * 车辆的品牌
     * @see #brandId
     */
    private String brand;

    /**
     * 车辆的系列
     * @see #seriesId
     */
    private String series;

    /**
     * 车辆城市的中文名
     */
    private String cityCnName;

    /**
     * 车辆区域的中文名
     */
    private String regionCnName;

    /**
     * 车辆城市的英文名
     */
    private String cityEnName;

    /**
     * 车辆区域的英文名
     */
    private String regionEnName;

    /**
     * 百度地图的位置
     */
    private BaiduMapLocation location;

    /**
     * 汽车排量含义
     */
    private String displacementMeaning;

    /**
     * 变速箱类型含义
     */
    private String gearBoxMeaning;

    /**
     * 颜色含义
     */
    private String colorMeaning;

    /**
     * 驱动类型含义
     */
    private String driveTypeMeaning;

    /**
     * 排放标准含义
     */
    private String emissionStandardMeaning;

    /**
     * 风格含义
     */
    private String styleMeaning;

    /**
     * 燃油类型含义
     */
    private String fuelTypeMeaning;

    /**
     * 标签
     */
    private String tags;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public Long getDeployeeId() {
        return deployeeId;
    }

    public void setDeployeeId(Long deployeeId) {
        this.deployeeId = deployeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public void setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    public void setEmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getWatchTimes() {
        return watchTimes;
    }

    public void setWatchTimes(Long watchTimes) {
        this.watchTimes = watchTimes;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCityCnName() {
        return cityCnName;
    }

    public void setCityCnName(String cityCnName) {
        this.cityCnName = cityCnName;
    }

    public String getRegionCnName() {
        return regionCnName;
    }

    public void setRegionCnName(String regionCnName) {
        this.regionCnName = regionCnName;
    }

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

    public BaiduMapLocation getLocation() {
        return location;
    }

    public void setLocation(BaiduMapLocation location) {
        this.location = location;
    }

    public String getDisplacementMeaning() {
        return displacementMeaning;
    }

    public void setDisplacementMeaning(String displacementMeaning) {
        this.displacementMeaning = displacementMeaning;
    }

    public String getGearBoxMeaning() {
        return gearBoxMeaning;
    }

    public void setGearBoxMeaning(String gearBoxMeaning) {
        this.gearBoxMeaning = gearBoxMeaning;
    }

    public String getColorMeaning() {
        return colorMeaning;
    }

    public void setColorMeaning(String colorMeaning) {
        this.colorMeaning = colorMeaning;
    }

    public String getDriveTypeMeaning() {
        return driveTypeMeaning;
    }

    public void setDriveTypeMeaning(String driveTypeMeaning) {
        this.driveTypeMeaning = driveTypeMeaning;
    }

    public String getEmissionStandardMeaning() {
        return emissionStandardMeaning;
    }

    public void setEmissionStandardMeaning(String emissionStandardMeaning) {
        this.emissionStandardMeaning = emissionStandardMeaning;
    }

    public String getStyleMeaning() {
        return styleMeaning;
    }

    public void setStyleMeaning(String styleMeaning) {
        this.styleMeaning = styleMeaning;
    }

    public String getFuelTypeMeaning() {
        return fuelTypeMeaning;
    }

    public void setFuelTypeMeaning(String fuelTypeMeaning) {
        this.fuelTypeMeaning = fuelTypeMeaning;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
