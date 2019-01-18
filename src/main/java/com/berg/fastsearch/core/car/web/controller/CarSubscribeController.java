package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.subscribe.ICarSubscribeService;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeQueryCondition;
import com.berg.fastsearch.core.car.web.dto.subscribe.SubscribeData;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@RequestMapping(value = {"/car/subscribe"})
@RestController
public class CarSubscribeController extends BaseController<Long, CarSubscribeDto, CarSubscribeQueryCondition> {

    @Autowired
    private ICarSubscribeService carSubscribeService;

    @Override
    protected IBaseService<Long, CarSubscribeDto, CarSubscribeQueryCondition> getService() {
        return carSubscribeService;
    }

    /**
     * 我的数据
     * @param status
     * @return
     */
    @GetMapping("/me")
    public ResponseData me(String status){
        return ResponseData.ofSuccess(carSubscribeService.findMe(status).getResult());
    }

    @PostMapping("/add")
    public ResponseData subscribe(@RequestBody SubscribeData subscribeData){
        carSubscribeService.subscribe(subscribeData);
        return new ResponseData();
    }

    /**
     * 取消预约
     * @param id
     * @return
     */
    @GetMapping("/del")
    public ResponseData del(Long id){
        carSubscribeService.del(id);
        return new ResponseData();
    }

    /**
     * 完成看车
     * @param id
     * @return
     */
    @PostMapping("/finishSaw")
    public ResponseData finishSaw(Long id){
        carSubscribeService.finishSaw(id);
        return new ResponseData();
    }

    /**
     * 完成交易
     * @param id
     * @return
     */
    @PostMapping("/finishTrade")
    public ResponseData finishTrade(Long id) throws Exception {
        carSubscribeService.finishTrade(id);
        return new ResponseData();
    }

}
