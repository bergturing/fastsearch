package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.car.service.ICarBrandService;
import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/car/brand")
@Controller
public class AdminCarBrandUrlController extends BaseUrlController<Long> {

    @Autowired
    private ICarBrandService carBrandService;

    @Override
    protected String getPrefix() {
        return "admin/car/brand";
    }

    @Override
    protected void editData(Long id, Model model) {
        //添加品牌数据
        model.addAttribute("brand", carBrandService.findOne(id));
    }
}
