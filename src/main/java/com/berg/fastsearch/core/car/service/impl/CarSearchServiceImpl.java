package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.car.web.dto.CarIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarTemplate;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import com.berg.fastsearch.core.enums.car.ValueBlock;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //设置区域数据
        SupportAddressDto region = supportAddressService.findOne(carTemplate.getRegionId());
        carTemplate.setRegionEnName(region.getEnName());

        return carTemplate;
    }

    @Override
    protected Class<CarIndexMessage> getIndexMessageClass() {
        return CarIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarQueryCondition condition) {
        //如果查询条件对象为null,就不进行查询条件的构建
        if(null == condition){
            return;
        }

        //处理排序
        buildSort(searchRequestBuilder, condition);

        //开始构建查询条件
        searchRequestBuilder.setQuery(buildQuery(condition));
    }

    @Override
    public String getIndexName() {
        return "car";
    }

    @Override
    public String getTypeName() {
        return "car";
    }

    /**
     * 构建排序
     * @param searchRequestBuilder      请求对象
     * @param condition                 查询条件
     */
    private void buildSort(final SearchRequestBuilder searchRequestBuilder, final CarQueryCondition condition){
        String orderBy = condition.getOrderBy();
        String orderDirection = condition.getOrderDirection();

        if(StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(orderDirection)){
            SortOrder sortOrder = SortOrder.DESC;

            if (StringUtils.equalsIgnoreCase(BaseQueryCondition.ORDER_ASC, orderDirection)){
                sortOrder = SortOrder.ASC;
            }

            searchRequestBuilder.addSort(orderBy, sortOrder);
        }

    }

    /**
     * 构建查询
     * @param condition                 查询条件
     */
    private QueryBuilder buildQuery(final CarQueryCondition condition){
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        //城市
        String cityEnName = condition.getCityEnName();
        if(StringUtils.isNotBlank(cityEnName)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarQueryCondition.FIELD_CITY_EN_NAME, cityEnName));
        }
        //区域
        String regionEnName = condition.getRegionEnName();
        if(StringUtils.isNotBlank(regionEnName)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarQueryCondition.FIELD_REGION_EN_NAME, regionEnName));
        }
        //品牌
        String brandCode = condition.getBrandCode();
        if(StringUtils.isNotBlank(brandCode)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarQueryCondition.FIELD_BRAND_CODE, brandCode));
        }
        //系列
        String seriesCode = condition.getSeriesCode();
        if(StringUtils.isNotBlank(seriesCode)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarQueryCondition.FIELD_SERIES_CODE, seriesCode));
        }
        //价格区间
        ValueBlock valueBlock = ValueBlock.matchPrice(condition.getPriceBlock());
        if(valueBlock!=null && valueBlock!=ValueBlock.ALL){
            //最大值
            int max = valueBlock.getMax();

            //范围查询
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(CarQueryCondition.FIELD_PRICE)
                    .gte(valueBlock.getMin());

            //判断最大值是否存在
            if(max!=-1){
                rangeQueryBuilder.lte(max);
            }

            //加入查询条件
            builder = builder
                    .must(rangeQueryBuilder);
        }
        //返回查询条件
        return builder;
    }
}
