package com.berg.fastsearch.core.system.code.web.controllers;

import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import com.berg.fastsearch.core.system.code.service.ICodeValueService;
import com.berg.fastsearch.core.system.code.web.dto.CodeValueDto;
import com.berg.fastsearch.core.system.code.web.dto.CodeValueQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@RequestMapping(value = {"/system/code/value"})
@RestController
public class CodeValueController extends BaseController<Long, CodeValueDto, CodeValueQueryCondition> {

    @Autowired
    private ICodeValueService codeValueService;

    @Override
    protected IBaseService<Long, CodeValueDto, CodeValueQueryCondition> getService() {
        return codeValueService;
    }
}
