package com.berg.fastsearch.core.car.service;

import com.berg.fastsearch.core.car.web.dto.CarBucketDto;
import com.berg.fastsearch.core.car.web.dto.CarIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public interface ICarSearchService extends ISearchService<Long, CarIndexMessage, CarQueryCondition>{

    /**
     * 聚合城市数据
     * @param cityEnName
     * @return
     */
    List<CarBucketDto> mapAggregate(String cityEnName);
}
