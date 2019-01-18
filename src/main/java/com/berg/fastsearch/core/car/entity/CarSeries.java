package com.berg.fastsearch.core.car.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>车辆系列实例</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Table(
        name = "fs_car_series"
)
@Entity
public class CarSeries extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column
    private Long brandId;

    @Column
    private String code;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
