package com.berg.fastsearch.user.car;

import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-9
 */
@Controller
public class UserCarUrlController extends BaseUrlController<Long> {
    @Override
    protected String getPrefix() {
        return null;
    }

    @RequestMapping("/user/car/list")
    @Override
    public String list(){
        return "user/car/list";
    }
}
