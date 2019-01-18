package com.berg.fastsearch.core.car.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>车辆标签关联实例</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Table(
        name = "fs_car_tag_ass"
)
@Entity
public class CarTagAss extends BaseEntity{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column
    private Long carId;

    @Column
    private Long carTagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCarTagId() {
        return carTagId;
    }

    public void setCarTagId(Long carTagId) {
        this.carTagId = carTagId;
    }
}
