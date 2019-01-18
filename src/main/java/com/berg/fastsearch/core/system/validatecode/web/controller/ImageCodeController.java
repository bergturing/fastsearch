package com.berg.fastsearch.core.system.validatecode.web.controller;

import com.berg.fastsearch.core.system.validatecode.entity.ImageCode;
import com.berg.fastsearch.core.system.validatecode.service.IValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
@RestController
public class ImageCodeController {

    @Autowired
    private IValidateCodeService<ImageCode> imageCodeServiceImpl;

    @GetMapping("/image/code")
    public void createCode(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        imageCodeServiceImpl.create(new ServletWebRequest(request, response));
    }
}
