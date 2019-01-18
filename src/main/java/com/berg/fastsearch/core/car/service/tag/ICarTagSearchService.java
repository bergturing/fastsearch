package com.berg.fastsearch.core.car.service.tag;

import com.berg.fastsearch.core.car.web.dto.tag.CarTagIndexMessage;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public interface ICarTagSearchService extends ISearchService<Long, CarTagIndexMessage, CarTagQueryCondition> {
}
