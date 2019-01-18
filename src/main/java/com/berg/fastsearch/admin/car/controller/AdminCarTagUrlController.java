package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.car.service.tag.ICarTagService;
import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@RequestMapping("/admin/car/tag")
@Controller
public class AdminCarTagUrlController extends BaseUrlController<Long> {

    @Autowired
    private ICarTagService carTagService;

    @Override
    protected String getPrefix() {
        return "admin/car/tag";
    }

    @Override
    protected void editData(Long id, Model model) {
        //添加tag对象
        model.addAttribute("tag", carTagService.findOne(id));
    }
}
