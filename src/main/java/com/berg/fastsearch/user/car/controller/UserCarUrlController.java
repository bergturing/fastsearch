package com.berg.fastsearch.user.car.controller;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import com.berg.fastsearch.user.car.dto.MapSearch;
import com.berg.fastsearch.user.car.dto.ValueBlock;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-9
 */
@RequestMapping("/user/car")
@Controller
public class UserCarUrlController{

    @Autowired
    private ISupportAddressService supportAddressService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public String list(@ModelAttribute CarQueryCondition searchBody,
                       Model model, HttpSession session,
                       RedirectAttributes redirectAttributes){

        if (searchBody.getCityEnName() == null) {
            String cityEnNameInSession = (String) session.getAttribute("cityEnName");
            if (cityEnNameInSession == null) {
                redirectAttributes.addAttribute("msg", "must_chose_city");
                return "redirect:/index";
            } else {
                searchBody.setCityEnName(cityEnNameInSession);
            }
        } else {
            session.setAttribute("cityEnName", searchBody.getCityEnName());
        }

        SupportAddressDto city = supportAddressService.findCity(searchBody.getCityEnName());
        if (city == null) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }
        model.addAttribute("currentCity", city);

        List<SupportAddressDto> addressResult = supportAddressService.findAllRegionsByCityEnName(searchBody.getCityEnName());

        if (addressResult == null) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }

        List<CarDto> cars = carService.findAll(searchBody);

        model.addAttribute("total", cars.size());
        model.addAttribute("cars", cars);

        //区域
        if (StringUtils.isBlank(searchBody.getRegionEnName())) {
            searchBody.setRegionEnName("*");
        }
        //品牌
        if (StringUtils.isBlank(searchBody.getBrandCode())) {
            searchBody.setBrandCode("*");
        }
        //系列
        if (StringUtils.isBlank(searchBody.getSeriesCode())) {
            searchBody.setSeriesCode("*");
        }
        //价格区间
        if (StringUtils.isBlank(searchBody.getPriceBlock())) {
            searchBody.setPriceBlock("*");
        }



        model.addAttribute("searchBody", searchBody);
        model.addAttribute("regions", addressResult);

        model.addAttribute("priceBlocks", ValueBlock.PRICE_BLOCK);

        model.addAttribute("currentPriceBlock", ValueBlock.matchPrice(searchBody.getPriceBlock()));


        return "user/car/list";
    }

    @GetMapping("/detail//{id:\\d+}")
    public String detail(@PathVariable("id") Long id,
                         Model model){
        if (id <= 0) {
            return "404";
        }

        CarDto carDto = carService.watchCar(id);
        if (carDto == null) {
            return "404";
        }

        Map<Level, SupportAddressDto>
                addressMap = supportAddressService.findCityAndRegion(carDto.getCityId(), carDto.getRegionId());

        SupportAddressDto city = addressMap.get(Level.CITY);
        SupportAddressDto region = addressMap.get(Level.REGION);

        model.addAttribute("city", city);
        model.addAttribute("region", region);

        UserDto userDto = userService.findOne(carDto.getDeployeeId());
        model.addAttribute("agent", userDto);
        model.addAttribute("car", carDto);

//        ServiceResult<Long> aggResult = searchService.aggregateDistrictHouse(city.getEnName(), region.getEnName(), houseDTO.getDistrict());
//        model.addAttribute("houseCountInDistrict", aggResult.getResult());

        return "user/car/detail";
    }

    @GetMapping("/map")
    public String map(@RequestParam(value = "cityEnName") String cityEnName,
                      Model model,
                      HttpSession session,
                      RedirectAttributes redirectAttributes){

        SupportAddressDto city = supportAddressService.findCity(cityEnName);
        if (city == null) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        } else {
            session.setAttribute("cityName", cityEnName);
            model.addAttribute("city", city);
        }

        List<SupportAddressDto> regions = supportAddressService.findAllRegionsByCityEnName(cityEnName);


//        ServiceMultiResult<HouseBucketDTO> serviceResult = searchService.mapAggregate(cityEnName);
//
        model.addAttribute("aggData", new ArrayList<>());
        model.addAttribute("total", 0);
        model.addAttribute("regions", regions);


        return "user/car/map";
    }

    @GetMapping("/map/cars")
    @ResponseBody
    public ResponseData rentMapHouses(@ModelAttribute MapSearch mapSearch) {
        if (mapSearch.getCityEnName() == null) {
            return ResponseData.ofMessage(HttpStatus.BAD_REQUEST.value(), "必须选择城市");
        }
//        ServiceMultiResult<HouseDTO> serviceMultiResult;
//        if (mapSearch.getLevel() < 13) {
//            serviceMultiResult = houseService.wholeMapQuery(mapSearch);
//        } else {
//            // 小地图查询必须要传递地图边界参数
//            serviceMultiResult = houseService.boundMapQuery(mapSearch);
//        }

        ResponseData response = ResponseData.ofSuccess(new ArrayList<>());
//        response.setMore(serviceMultiResult.getTotal() > (mapSearch.getStart() + mapSearch.getSize()));
        return response;

    }
}
