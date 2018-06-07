package com.berg.fastsearch.admin.car.controller;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.car.service.*;
import com.berg.fastsearch.core.car.service.brand.ICarBrandService;
import com.berg.fastsearch.core.car.service.picture.ICarPictureService;
import com.berg.fastsearch.core.car.service.series.ICarSeriesService;
import com.berg.fastsearch.core.car.service.tag.ICarTagService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.enums.car.*;
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
 * @apiNote Created on 18-5-7
 */
@RequestMapping("/admin/car")
@Controller
public class AdminCarUrlController extends BaseUrlController<Long>{

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarPictureService carPictureService;

    @Autowired
    private ICarTagService carTagService;

    @Autowired
    private ISupportAddressService supportAddressService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarSeriesService carSeriesService;

    @Override
    protected String getPrefix() {
        return "admin/car";
    }

    @Override
    protected void listData(Model model) {
        //车辆的状态
        model.addAttribute("statuses", Status.values());
    }

    @Override
    protected void addData(Model model){
        //设置基本属性
        baseData(model);
    }

    @Override
    public void editData(Long id, Model model){
        //设置基本属性
        baseData(model);

        CarDto carDto = carService.findOne(id);
        //设置车辆信息
        model.addAttribute("car", carDto);

        //查询当前汽车的标签
        model.addAttribute("tags", carTagService.findByCarId(id));
        //查询当前汽车的图片
        model.addAttribute("pictures", carPictureService.findByCarId(id));

        //设置系列数据
        CarSeriesQueryCondition carSeriesQueryCondition = new CarSeriesQueryCondition();
        carSeriesQueryCondition.setBrandId(carDto.getBrandId());
        model.addAttribute("series", carSeriesService.findAll(carSeriesQueryCondition).getResult());

        //设置区域数据
        SupportAddressQueryCondition supportAddressQueryCondition = new SupportAddressQueryCondition();
        supportAddressQueryCondition.setBelongTo(carDto.getCityId());
        supportAddressQueryCondition.setLevel(Level.REGION.getCode());
        model.addAttribute("regions", supportAddressService.findAll(supportAddressQueryCondition).getResult());
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
        model.addAttribute("allTags", carTagService.findAll(null).getResult());
    }
}
