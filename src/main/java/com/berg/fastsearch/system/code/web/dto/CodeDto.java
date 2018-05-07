package com.berg.fastsearch.system.code.web.dto;

import com.berg.fastsearch.system.base.web.dto.BaseDto;
import org.hibernate.validator.constraints.Length;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public class CodeDto extends BaseDto<Long> {
    private static final long serialVersionUID = -7350096259804651991L;

    /**
     * 代码维护的编码
     */
    @Length(max = 32)
    private String code;

    /**
     * 代码维护的描述
     */
    @Length(max = 255)
    private String description;

    /**
     * 用标识:Y-禁用,N-启用
     */
    @Length(max = 1)
    private String enabledFlag;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }
}
