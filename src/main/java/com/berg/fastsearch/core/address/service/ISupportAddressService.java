package com.berg.fastsearch.core.address.service;

import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.system.base.entity.ServiceResult;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.search.entity.BaiduMapLocation;

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
     * @param cityEnName
     * @return
     */
    List<SupportAddressDto> findAllRegionsByCityEnName(String cityEnName);

    /**
     * 根据城市英文简写获取城市详细信息
     * @param cityEnName
     * @return
     */
    SupportAddressDto findCity(String cityEnName);

    /**
     * 根据英文简写获取具体区域的信息
     * @param cityId
     * @param regionId
     * @return
     */
    Map<Level, SupportAddressDto> findCityAndRegion(Long cityId, Long regionId);

    /**
     *
     * @param level
     * @return
     */
    List<SupportAddressDto> findByLevel(String level);

    /**
     * 根据城市以及具体地位获取百度地图的经纬度
     */
    ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address);
}
