package com.berg.fastsearch.core.car.web.dto.subscribe;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-6-6
 */
public class SubscribeData implements Serializable {
    private static final long serialVersionUID = -1832713097222000977L;

    private List<Long> ids;

    private String telephone;

    private Date orderTime;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
