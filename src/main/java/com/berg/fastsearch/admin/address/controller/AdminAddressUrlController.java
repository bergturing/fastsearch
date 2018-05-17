package com.berg.fastsearch.admin.address.controller;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
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
 * @apiNote Created on 18-5-16
 */
@RequestMapping("/admin/address")
@Controller
public class AdminAddressUrlController extends BaseUrlController<Long> {

    @Autowired
    private ISupportAddressService supportAddressService;

    @Override
    protected String getPrefix() {
        return "admin/address";
    }

    @Override
    protected void addData(Model model) {
        baseData(model);
    }

    @Override
    protected void editData(Long id, Model model) {
        baseData(model);
        //设置地址对象
        model.addAttribute("address", supportAddressService.findOne(id));
    }

    /**
     * 基础的数据
     * @param model     model 对象
     */
    private void baseData(final Model model){
        //设置所有的城市
        SupportAddressQueryCondition supportAddressQueryCondition = new SupportAddressQueryCondition();
        supportAddressQueryCondition.setLevel(Level.CITY.getCode());
        model.addAttribute("citys", supportAddressService.findAll(supportAddressQueryCondition));

        //添加行政级别
        model.addAttribute("levels", Level.values());
    }
}
