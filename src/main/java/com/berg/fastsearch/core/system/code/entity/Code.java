package com.berg.fastsearch.core.system.code.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>代码维护头对象</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Table(
        name = "sys_code_b"
)
@Entity
public class Code extends BaseEntity {
    /**
     * 代码维护头表的主键
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * 代码维护的编码
     */
    @Column
    @Length(max = 32)
    private String code;

    /**
     * 代码维护的描述
     */
    @Column
    @Length(max = 255)
    private String description;

    /**
     * 用标识:Y-禁用,N-启用
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
