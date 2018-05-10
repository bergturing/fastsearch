package com.berg.fastsearch.core.car.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Table(
        name = "fs_cars"
)
@Entity
public class Car extends BaseEntity {
    /**
     * 车辆的主键
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * 发布者的id,用户表(sys_users)的主键
     */
    @Column
    private Long deployeeId;

    /**
     * 汽车的标题
     */
    @Column
    private String title;

    /**
     * 汽车的价格
     */
    @Column
    private BigDecimal price;

    /**
     * 汽车的座位数
     */
    @Column
    private Integer seats;

    /**
     * 汽车的排量
     */
    @Column
    private BigDecimal displacement;

    /**
     * 汽车的里程
     */
    @Column
    private BigDecimal mileage;

    /**
     * 车龄
     */
    @Column
    private Integer age;

    /**
     * 汽车变速箱类型,代码维护code:FS.CAR_GEAR_BOXS
     */
    @Column
    private String gearBox;

    /**
     * 汽车的颜色,代码维护code:FS.CAR_COLORS
     */
    @Column
    private String color;

    /**
     * 汽车的驱动类型,代码维护code:FS_CAR_DRIVE_TYPES
     */
    @Column
    private String driveType;

    /**
     * 汽车的排放标准,代码维护code:FS_CAR_DMISSION_STANDARDS
     */
    @Column
    private String emissionStandard;

    /**
     * 车型,代码维护code:FS_CAR_STYLES
     */
    @Column
    private String style;

    /**
     * 燃油类型,代码维护code:FS_CAR_FUEL_TYPES
     */
    @Column
    private String fuelType;

    /**
     * 被看次数
     */
    @Column
    private Long watchTimes;

    /**
     * 城市标记缩写 如 北京bj
     */
    @Column
    private String cityEnName;

    /**
     * 地区英文简写 如昌平区 cpq
     */
    @Column
    private String regionEnName;

    /**
     * 详细地址
     */
    @Column
    private String address;

    /**
     * 封面
     */
    @Column
    private String cover;

    /**
     * 汽车的状态
     */
    @Column
    private String status;

    /**
     * 描述
     */
    @Column
    private String description;

    /**
     * 汽车创建时间
     */
    @Column
    private Date createTime;

    /**
     * 上次更新记录时间
     */
    @Column
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
