package com.berg.fastsearch.core.car.service;

import com.berg.fastsearch.core.car.web.dto.CarTagAssIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarTagAssQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public interface ICarTagAssSearchService extends ISearchService<Long, CarTagAssIndexMessage, CarTagAssQueryCondition> {
}
