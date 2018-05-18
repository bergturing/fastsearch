package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.service.tag.ICarTagAssService;
import com.berg.fastsearch.core.car.service.tag.ICarTagService;
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
@RequestMapping("/admin/car/tag/ass")
@Controller
public class AdminCarTagAssUrlController extends BaseUrlController<Long> {

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarTagService carTagService;

    @Autowired
    private ICarTagAssService carTagAssService;

    @Override
    protected String getPrefix() {
        return "admin/car/tag/ass";
    }

    @Override
    protected void addData(Model model) {
        baseData(model);
    }

    @Override
    protected void editData(Long id, Model model) {
        baseData(model);

        //设置分派对象
        model.addAttribute("tagAss", carTagAssService.findOne(id));
    }

    /**
     * 基础的数据
     * @param model     model 对象
     */
    private void baseData(final Model model){
        //设置所有的车辆
        model.addAttribute("cars", carService.findAll(null));
        //设置所有的标签
        model.addAttribute("tags", carTagService.findAll(null));
    }
}
