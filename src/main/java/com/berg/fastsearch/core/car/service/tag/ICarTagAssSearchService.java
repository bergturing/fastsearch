package com.berg.fastsearch.core.car.service.tag;

import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssIndexMessage;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public interface ICarTagAssSearchService extends ISearchService<Long, CarTagAssIndexMessage, CarTagAssQueryCondition> {
}
