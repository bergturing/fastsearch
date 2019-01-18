package com.berg.fastsearch.core.system.code.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>代码维护值表</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Table(
        name = "sys_code_value_b"
)
@Entity
public class CodeValue extends BaseEntity {
    /**
     * 代码维护值的主键
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * 代码维护头表的id,代码维护头表(sys_code_b)的主键
     */
    @Column
    @NotNull
    private Long codeId;

    /**
     * 代码的值
     */
    @Column
    @NotNull
    @Length(max = 150)
    private String value;

    /**
     * 代码的含义
     */
    @Column
    @NotNull
    @Length(max = 150)
    private String meaning;

    /**
     * 描述
     */
    @Column
    @Length(max = 255)
    private String description;

    /**
     * 标识
     */
    @Column
    @Length(max = 255)
    private String tag;

    /**
     * 禁用标识:Y-禁用,N-启用
     */
    @Column
    @Length(max = 1)
    private String enabledFlag;

    /**
     * 数据创建时间
     */
    @Column
    private Date createTime;

    /**
     * 最近更新时间
     */
    @Column
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
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
