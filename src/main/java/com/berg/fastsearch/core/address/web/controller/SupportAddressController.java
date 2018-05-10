package com.berg.fastsearch.core.address.web.controller;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@RequestMapping(value = {"/address"})
@RestController
public class SupportAddressController extends BaseController<Long, SupportAddressDto, SupportAddressQueryCondition> {

    @Autowired
    private ISupportAddressService supportAddressService;

    @Override
    protected IBaseService<Long, SupportAddressDto, SupportAddressQueryCondition> getService() {
        return supportAddressService;
    }
}
