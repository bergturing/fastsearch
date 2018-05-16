package com.berg.fastsearch.core.address.web.dto;

import com.berg.fastsearch.core.system.search.template.BaseTemplate;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public class SupportAddressTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = 4172320855798908593L;

    private Long belongTo;

    private String level;

    private String enName;

    private String cnName;

    public Long getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Long belongTo) {
        this.belongTo = belongTo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
