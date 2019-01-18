package com.berg.fastsearch.core.system.code.service;

import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.code.entity.Code;
import com.berg.fastsearch.core.system.code.web.dto.CodeDto;
import com.berg.fastsearch.core.system.code.web.dto.CodeQueryCondition;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public interface ICodeService extends IBaseService<Long, CodeDto, CodeQueryCondition> {
}
