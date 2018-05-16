package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.car.service.*;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.enums.car.*;
import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@RequestMapping("/admin/car")
@Controller
public class AdminCarUrlController{

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarPictureService carPictureService;

    @Autowired
    private ICarTagService  carTagService;

    @Autowired
    private ISupportAddressService supportAddressService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarSeriesService carSeriesService;

    /**
     * 列表界面
     * @return      列表界面的路径
     */
    @GetMapping("/list")
    public String list(){
        return "admin/car/list";
    }

    /**
     * 新增车辆的界面
     * @return      新增车辆界面路径
     */
    @GetMapping("/add")
    public String add(Model model){
        //设置基本属性
        baseData(model);

        return "admin/car/add";
    }

    /**
     * 编辑车辆的界面
     * @return      编辑车辆的路径
     */
    @GetMapping("/edit")
    public String edit(Long id, Model model){
        //设置基本属性
        baseData(model);

        //查询当前汽车的标签
        model.addAttribute("tags", carTagService.findByCarId(id));
        //查询当前汽车的图片
        model.addAttribute("pictures", carPictureService.findByCarId(id));
        //设置车辆信息
        model.addAttribute("car", carService.findOne(id));

        return "admin/car/edit";
    }

    /**
     * 基础的数据
     * @param model     model 对象
     */
    private void baseData(final Model model){
        //颜色
        model.addAttribute("colors", Color.values());
        //驱动类型
        model.addAttribute("driveTypes", DriveType.values());
        //排放标准
        model.addAttribute("emissionStandards", EmissionStandard.values());
        //燃油类型
        model.addAttribute("fuelTypes", FuelType.values());
        //变速箱类型
        model.addAttribute("gearBoxs", GearBox.values());
        //车型
        model.addAttribute("styles", Style.values());
        //所有的标签
        model.addAttribute("allTags", carTagService.findAll(null));
        //所有的城市
        model.addAttribute("citys", supportAddressService.findByLevel(Level.CITY.getValue()));
        //品牌的信息
        model.addAttribute("brands", carBrandService.findAll(null));
    }
}
