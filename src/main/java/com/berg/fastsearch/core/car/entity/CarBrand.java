package com.berg.fastsearch.core.car.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>车辆品牌实例</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Table(
        name = "fs_car_brands"
)
@Entity
public class CarBrand extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
