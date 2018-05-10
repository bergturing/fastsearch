package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public class CarDto extends BaseDto<Long> {
    private static final long serialVersionUID = 8908498609466570322L;

    /**
     * 发布者的id,用户表(sys_users)的主键
     */
    private Long deployeeId;

    /**
     * 汽车的标题
     */
    @NotNull
    private String title;

    /**
     * 汽车的价格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 汽车的座位数
     */
    @NotNull
    private Integer seats;

    /**
     * 汽车的排量
     */
    @NotNull
    private BigDecimal displacement;

    /**
     * 汽车的里程
     */
    @NotNull
    private BigDecimal mileage;

    /**
     * 车龄
     */
    @NotNull
    private Integer age;

    /**
     * 汽车变速箱类型,代码维护code:FS.CAR_GEAR_BOXS
     */
    @NotNull
    private String gearBox;

    /**
     * 汽车的颜色,代码维护code:FS.CAR_COLORS
     */
    @NotNull
    private String color;

    /**
     * 汽车的驱动类型,代码维护code:FS_CAR_DRIVE_TYPES
     */
    @NotNull
    private String driveType;

    /**
     * 汽车的排放标准,代码维护code:FS_CAR_DMISSION_STANDARDS
     */
    @NotNull
    private String emissionStandard;

    /**
     * 车型,代码维护code:FS_CAR_STYLES
     */
    @NotNull
    private String style;

    /**
     * 燃油类型,代码维护code:FS_CAR_FUEL_TYPES
     */
    @NotNull
    private String fuelType;

    /**
     * 被看次数
     */
    private Long watchTimes;

    /**
     * 城市标记缩写 如 北京bj
     */
    private String cityEnName;

    /**
     * 地区英文简写 如昌平区 cpq
     */
    private String regionEnName;

    /**
     * 详细地址
     */
    @NotNull
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
     * 车辆的照片
     */
    private List<Photo> photos;

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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
