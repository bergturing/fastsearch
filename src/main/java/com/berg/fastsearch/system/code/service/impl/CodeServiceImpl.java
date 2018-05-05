package com.berg.fastsearch.system.code.service.impl;

import com.berg.fastsearch.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.system.code.web.dto.CodeDto;
import com.berg.fastsearch.system.code.entity.Code;
import com.berg.fastsearch.system.code.repository.CodeRepository;
import com.berg.fastsearch.system.code.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class CodeServiceImpl extends AbstractBaseServiceImpl<CodeDto, Code, Long> implements ICodeService {
    @Autowired
    private CodeRepository codeRepository;

    @Override
    protected JpaRepository<Code, Long> getRepository() {
        return codeRepository;
    }

    @Override
    protected CodeDto createDto() {
        return new CodeDto();
    }

    @Override
    protected Code createEntity() {
        return new Code();
    }
}
