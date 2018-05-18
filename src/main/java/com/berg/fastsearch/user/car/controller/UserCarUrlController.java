package com.berg.fastsearch.user.car.controller;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarBucketDto;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.enums.car.Status;
import com.berg.fastsearch.core.enums.car.SubscribeStatus;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import com.berg.fastsearch.core.system.base.entity.ServiceMultiResult;
import com.berg.fastsearch.user.car.dto.MapSearch;
import com.berg.fastsearch.core.enums.car.ValueBlock;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-9
 */
@RequestMapping(value = {"/car", "/user/car"})
@Controller
public class UserCarUrlController{

    @Autowired
    private ISupportAddressService supportAddressService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICarSearchService carSearchService;

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

        //条件查询所有已经通过审核的车辆
        searchBody.setStatus(Status.PASSED.getCode());
        List<CarDto> cars = carService.findAll(searchBody).getResult();

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
                         Model model) throws Exception {
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

        //所有的预约的状态
        model.addAttribute("subscribeStatus", SubscribeStatus.values());

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

        //获取聚合结果
        List<CarBucketDto> carBucketDtos = carSearchService.mapAggregate(cityEnName);

        model.addAttribute("aggData", carBucketDtos);
        model.addAttribute("total", carBucketDtos.size());
        model.addAttribute("regions", regions);


        return "user/car/map";
    }

    @GetMapping("/map/cars")
    @ResponseBody
    public ResponseData rentMapHouses(@ModelAttribute MapSearch mapSearch) {
        if (mapSearch.getCityEnName() == null) {
            return ResponseData.ofMessage(HttpStatus.BAD_REQUEST.value(), "必须选择城市");
        }


        CarQueryCondition carQueryCondition = new CarQueryCondition();
        BeanUtils.copyProperties(mapSearch, carQueryCondition);
        //全查询,需要将边界属性设置为空
        if (mapSearch.getLevel() < 13) {
            carQueryCondition.setLeftLatitude(null);
            carQueryCondition.setLeftLongitude(null);
            carQueryCondition.setRightLatitude(null);
            carQueryCondition.setRightLongitude(null);
        }

        //查询结果
        ServiceMultiResult<CarDto> multiResult = carService.findAll(carQueryCondition);

        ResponseData response = ResponseData.ofSuccess(multiResult.getResult());
        response.setMore(multiResult.getTotal() > (mapSearch.getStart() + mapSearch.getSize()));
        return response;
    }
}
