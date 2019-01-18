package com.berg.fastsearch.core.car.service.subscribe;

import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeQueryCondition;
import com.berg.fastsearch.core.car.web.dto.subscribe.SubscribeData;
import com.berg.fastsearch.core.system.base.entity.ServiceMultiResult;
import com.berg.fastsearch.core.system.base.entity.ServiceResult;
import com.berg.fastsearch.core.system.base.service.IBaseService;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public interface ICarSubscribeService extends IBaseService<Long, CarSubscribeDto, CarSubscribeQueryCondition> {

    /**
     *
     * @param carId
     * @param userId
     * @return
     */
    CarSubscribeDto findByCarIdAndUserId(Long carId, Long userId);

    /**
     *
     * @param status
     * @return
     */
    ServiceMultiResult<CarSubscribeDto> findMe(String status);

    /**
     * 取消预约
     * @param id
     */
    ServiceResult<CarSubscribeDto> del(Long id);

    /**
     *
     * @param subscribeData
     * @return
     */
    ServiceResult<CarSubscribeDto> subscribe(SubscribeData subscribeData);

    /**
     *
     * @param id
     * @return
     */
    ServiceResult<CarSubscribeDto> finishSaw(Long id);

    /**
     *
     * @param id
     * @return
     */
    ServiceResult<CarSubscribeDto> finishTrade(Long id) throws Exception;
}
