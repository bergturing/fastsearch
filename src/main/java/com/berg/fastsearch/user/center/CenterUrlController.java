package com.berg.fastsearch.user.center;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@RequestMapping(value = {"/center", "/user/center"})
@Controller
public class CenterUrlController {

    /**
     *
     * @return
     */
    @GetMapping("")
    public String center(){
        return "user/center/center";
    }

    /**
     *
     * @return
     */
    @GetMapping("/collect")
    public String collect(){
        return "user/center/collect";
    }

    /**
     *
     * @return
     */
    @GetMapping("/subscribe")
    public String subscribe(){
        return "user/center/subscribe";
    }

    /**
     *
     * @return
     */
    @GetMapping("/logs")
    public String logs(){
        return "user/center/logs";
    }

    /**
     *
     * @return
     */
    @GetMapping("/message")
    public String message(){
        return "user/center/message";
    }
}
