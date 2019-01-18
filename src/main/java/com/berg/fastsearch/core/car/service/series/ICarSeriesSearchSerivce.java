package com.berg.fastsearch.core.car.service.series;

import com.berg.fastsearch.core.car.web.dto.series.CarSeriesIndexMessage;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public interface ICarSeriesSearchSerivce extends ISearchService<Long, CarSeriesIndexMessage, CarSeriesQueryCondition> {
}
