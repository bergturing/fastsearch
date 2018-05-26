package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.car.service.brand.ICarBrandService;
import com.berg.fastsearch.core.car.service.series.ICarSeriesService;
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
@RequestMapping("/admin/car/series")
@Controller
public class AdminCarSeriesUrlController extends BaseUrlController<Long> {

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarSeriesService carSeriesService;

    @Override
    protected String getPrefix() {
        return "admin/car/series";
    }

    @Override
    protected void addData(Model model) {
        baseData(model);
    }

    @Override
    protected void editData(Long id, Model model) {
        baseData(model);

        //设置当前系列的数据
        model.addAttribute("series", carSeriesService.findOne(id));
    }

    /**
     * 基础的数据
     * @param model     model 对象
     */
    private void baseData(final Model model){
        //设置所有的品牌
        model.addAttribute("brands", carBrandService.findAll(null).getResult());
    }
}
