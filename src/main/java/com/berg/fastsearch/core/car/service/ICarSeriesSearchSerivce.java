package com.berg.fastsearch.core.car.service;

import com.berg.fastsearch.core.car.web.dto.CarSeriesIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarSeriesQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public interface ICarSeriesSearchSerivce extends ISearchService<Long, CarSeriesIndexMessage, CarSeriesQueryCondition> {
}
