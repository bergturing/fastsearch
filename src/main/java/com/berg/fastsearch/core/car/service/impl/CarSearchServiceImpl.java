package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.car.web.dto.*;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagDto;
import com.berg.fastsearch.core.system.base.entity.ServiceResult;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.search.entity.BaiduMapLocation;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import com.berg.fastsearch.core.enums.car.ValueBlock;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransportClient esClient;

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
    protected CarTemplate map(Long id) throws Exception {
        CarTemplate carTemplate = new CarTemplate();

        CarDto carDto = carService.findOne(id);

        //设置车辆的基本信息
        BeanUtils.copyProperties(carDto, carTemplate);

        List<CarTagDto> tags = carDto.getTags();
        if(CollectionUtils.isNotEmpty(tags)){
            StringBuilder stringBuilder = new StringBuilder("");

            for (CarTagDto tag : tags) {
                stringBuilder.append(tag.getName()).append(",");
            }

            carTemplate.setTags(stringBuilder.toString());
        }

        //设置城市信息
        SupportAddressDto city = supportAddressService.findOne(carTemplate.getCityId());
        carTemplate.setCityEnName(city.getEnName());

        //设置区域数据
        SupportAddressDto region = supportAddressService.findOne(carTemplate.getRegionId());
        carTemplate.setRegionEnName(region.getEnName());

        //设置位置
        String address = city.getCnName() + region.getCnName() + carDto.getAddress();
        ServiceResult<BaiduMapLocation> location = supportAddressService.getBaiduMapLocation(city.getCnName(), address);
        if (!location.isSuccess()) {
            throw new Exception("获取地理位置失败");
        }
        carTemplate.setLocation(location.getResult());

        return carTemplate;
    }

    @Override
    protected Class<CarTemplate> getTemplateClass() {
        return CarTemplate.class;
    }

    @Override
    protected CarIndexMessage getMessage() {
        CarIndexMessage message = new CarIndexMessage();
        message.setProcessServiceName("carSearchServiceImpl");
        return message;
    }

    @Override
    protected Class<CarIndexMessage> getMessageClass() {
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

        //地理位置
        Double leftLatitude = condition.getLeftLatitude();
        Double leftLongitude = condition.getLeftLongitude();
        Double rightLatitude = condition.getRightLatitude();
        Double rightLongitude = condition.getRightLongitude();
        if(leftLatitude!=null && leftLatitude>0){
            if(leftLongitude!=null && leftLongitude>0){
                if(rightLatitude!=null && rightLatitude>0){
                    if(rightLongitude!=null && rightLongitude>0){
                        builder.must(
                                QueryBuilders
                                        .geoBoundingBoxQuery("location")
                                        .setCorners(
                                                new GeoPoint(leftLatitude, leftLongitude),
                                                new GeoPoint(rightLatitude, rightLongitude)
                                        ));
                    }
                }
            }
        }

        //状态
        String status = condition.getStatus();
        if(StringUtils.isNotBlank(status)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarQueryCondition.FIELD_STATUS, status));
        }

        String keywords = condition.getKeywords();
        if(StringUtils.isNotBlank(keywords)){
            builder = builder.must(
                    QueryBuilders.multiMatchQuery(keywords,
                            CarQueryCondition.FIELD_CITY_EN_NAME,
                            CarQueryCondition.FIELD_REGION_EN_NAME,
                            CarQueryCondition.FIELD_CITY_CN_NAME,
                            CarQueryCondition.FIELD_REGION_CN_NAME,
                            CarQueryCondition.FIELD_SERIES_CODE,
                            CarQueryCondition.FIELD_SERIES,
                            CarQueryCondition.FIELD_BRAND_CODE,
                            CarQueryCondition.FIELD_BRAND,
                            CarQueryCondition.FIELD_TAGS,
                            CarQueryCondition.FIELD_GEAR_BOX_MEANING,
                            CarQueryCondition.FIELD_COLOR_MEANING,
                            CarQueryCondition.FIELD_DRIVE_TYPE_MEANING,
                            CarQueryCondition.FIELD_EMISSION_STANDAR_MEANING,
                            CarQueryCondition.FIELD_STYLE_MEANING,
                            CarQueryCondition.FIELD_FUEL_TYPE_MEANING,
                            CarQueryCondition.FIELD_ADDRESS
                    ));
        }

        //返回查询条件
        return builder;
    }

    @Override
    public List<CarBucketDto> mapAggregate(String cityEnName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(CarQueryCondition.FIELD_CITY_EN_NAME, cityEnName));

        AggregationBuilder aggBuilder = AggregationBuilders.terms(CarQueryCondition.AGG_REGION)
                .field(CarQueryCondition.FIELD_REGION_EN_NAME);
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(getIndexName())
                .setTypes(getTypeName())
                .setQuery(boolQuery)
                .addAggregation(aggBuilder);

        logger.debug(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        List<CarBucketDto> buckets = new ArrayList<>();
        if (response.status() != RestStatus.OK) {
            logger.warn("Aggregate status is not ok for " + requestBuilder);
            return buckets;
        }

        Terms terms = response.getAggregations().get(CarQueryCondition.AGG_REGION);
        for (Terms.Bucket bucket : terms.getBuckets()) {
            buckets.add(new CarBucketDto(bucket.getKeyAsString(), bucket.getDocCount()));
        }

        return buckets;
    }
}
