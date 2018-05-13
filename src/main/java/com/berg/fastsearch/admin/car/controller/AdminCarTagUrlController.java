package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@RequestMapping("/admin/car/tag")
@Controller
public class AdminCarTagUrlController extends BaseUrlController<Long> {
    @Override
    protected String getPrefix() {
        return "admin/car/tag";
    }
}
