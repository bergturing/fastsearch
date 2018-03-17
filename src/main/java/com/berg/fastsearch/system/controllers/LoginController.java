package com.berg.fastsearch.system.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@RestController
public class LoginController {

    @RequestMapping(value = {"/login.html", "/login"})
    public String hello(){
        return "hello login";
    }
}
