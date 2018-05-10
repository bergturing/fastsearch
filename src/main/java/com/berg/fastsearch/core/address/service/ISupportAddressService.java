package com.berg.fastsearch.core.address.service;

import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public interface ISupportAddressService extends IBaseService<Long, SupportAddressDto, SupportAddressQueryCondition> {

    /**
     * 根据城市英文简写获取该城市所有支持的区域信息
     * @param cityName
     * @return
     */
    List<SupportAddressDto> findAllRegionsByCityName(String cityName);

    /**
     * 根据城市英文简写获取城市详细信息
     * @param cityEnName
     * @return
     */
    SupportAddressDto findCity(String cityEnName);

    /**
     * 根据英文简写获取具体区域的信息
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    Map<SupportAddress.Level, SupportAddressDto> findCityAndRegion(String cityEnName, String regionEnName);
}
