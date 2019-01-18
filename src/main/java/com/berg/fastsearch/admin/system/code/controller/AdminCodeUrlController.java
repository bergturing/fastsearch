package com.berg.fastsearch.admin.system.code.controller;

import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@RequestMapping("/admin/system/code")
@Controller
public class AdminCodeUrlController extends BaseUrlController<Long> {

    @Override
    protected String getPrefix() {
        return "admin/system/code";
    }
}
