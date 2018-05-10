package com.berg.fastsearch.user.center;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@RequestMapping(value = {"", "/user"})
@Controller
public class CenterUrlController {

    @GetMapping("/center")
    public String center(){
        return "user/center/center";
    }
}
