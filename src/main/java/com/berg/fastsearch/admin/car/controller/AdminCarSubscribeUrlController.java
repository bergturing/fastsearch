package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.enums.car.SubscribeStatus;
import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@RequestMapping("/admin/car/subscribe")
@Controller
public class AdminCarSubscribeUrlController extends BaseUrlController<Long> {
    @Override
    protected String getPrefix() {
        return "admin/car/subscribe";
    }

    @Override
    protected void listData(Model model) {
        //预约的状态
        model.addAttribute("statuses", SubscribeStatus.values());
    }
}
