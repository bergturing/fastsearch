package com.berg.fastsearch.account.dto;

import com.berg.fastsearch.system.dto.BaseDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public class RoleDto extends BaseDto{
    private static final long serialVersionUID = -2997485158386765627L;

    /**
     * 角色的主键
     */
    private Long id;

    /**
     * 角色名
     */
    @NotNull
    @Length(max = 32)
    private String name;

    /**
     * 描述
     */
    @Length(max = 255)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
