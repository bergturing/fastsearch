package com.berg.fastsearch.core.system.code.web.controllers;

import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import com.berg.fastsearch.core.system.code.entity.Code;
import com.berg.fastsearch.core.system.code.service.ICodeService;
import com.berg.fastsearch.core.system.code.web.dto.CodeDto;
import com.berg.fastsearch.core.system.code.web.dto.CodeQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@RequestMapping(value = {"/system/code"})
@RestController
public class CodeController extends BaseController<Long, CodeDto, Code, CodeQueryCondition> {

    @Autowired
    private ICodeService codeService;

    @Override
    protected IBaseService<CodeDto, Code, Long> getService() {
        return codeService;
    }
}
