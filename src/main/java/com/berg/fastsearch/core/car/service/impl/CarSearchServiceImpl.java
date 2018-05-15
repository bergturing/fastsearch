package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.car.entity.CarIndexMessage;
import com.berg.fastsearch.core.car.entity.CarTemplate;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.car.service.ICarSeriesService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.car.web.dto.CarSeriesDto;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>车辆ES搜索服务对象</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Service
public class CarSearchServiceImpl
        extends AbstractSearchService<Long, CarQueryCondition, CarIndexMessage, CarTemplate>
        implements ICarSearchService{

    /**
     * 车辆的服务对象
     */
    @Autowired
    private ICarService carService;

    /**
     * 地点的服务对象
     */
    @Autowired
    private ISupportAddressService supportAddressService;

    @Override
    protected CarTemplate map(Long id) {
        CarTemplate carTemplate = new CarTemplate();

        //设置车辆的基本信息
        BeanUtils.copyProperties(carService.findOne(id), carTemplate);;

        //设置城市信息
        SupportAddressDto city = supportAddressService.findOne(carTemplate.getCityId());
        carTemplate.setCityEnName(city.getEnName());

        SupportAddressDto region = supportAddressService.findOne(carTemplate.getRegionId());
        carTemplate.setRegionEnName(city.getEnName());


        return carTemplate;
    }

    @Override
    protected Class<CarIndexMessage> getIndexMessageClass() {
        return CarIndexMessage.class;
    }

    @Override
    protected SearchRequestBuilder buildQuery(SearchRequestBuilder searchRequestBuilder, CarQueryCondition condition) {
        //如果查询条件对象为null,就不进行查询条件的构建
        if(null == condition){
            return null;
        }

        //开始构建查询条件

        if(StringUtils.isNotBlank(condition.getCityEnName())){
            searchRequestBuilder = searchRequestBuilder
                    .setQuery(QueryBuilders.termQuery(CarQueryCondition.FIELD_CITY_EN_NAME, condition.getCityEnName()));
        }


        return searchRequestBuilder;
    }

    @Override
    public String getIndexName() {
        return "fastsearch";
    }

    @Override
    public String getTypeName() {
        return "car";
    }
}
