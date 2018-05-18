package com.berg.fastsearch.core.address.service.impl;

import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.repository.SupportAddressRepository;
import com.berg.fastsearch.core.address.service.ISupportAddressSearchService;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.address.web.dto.SupportAddressIndexMessage;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.system.base.entity.ServiceResult;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.entity.BaiduMapLocation;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Service
public class SupportAddressServiceImpl
        extends AbstractBaseServiceImpl<Long, SupportAddressDto, SupportAddress, SupportAddressQueryCondition>
        implements ISupportAddressService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String BAIDU_MAP_KEY = "A5sxa4DVip54FU3HauB3sj4VntyAfAbA";

    private static final String BAIDU_MAP_GEOCONV_API = "http://api.map.baidu.com/geocoder/v2/?";

    /**
     * POI数据管理接口
     */
    private static final String LBS_CREATE_API = "http://api.map.baidu.com/geodata/v3/poi/create";

    private static final String LBS_QUERY_API = "http://api.map.baidu.com/geodata/v3/poi/list?";

    private static final String LBS_UPDATE_API = "http://api.map.baidu.com/geodata/v3/poi/update";

    private static final String LBS_DELETE_API = "http://api.map.baidu.com/geodata/v3/poi/delete";

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private ISupportAddressSearchService supportAddressSearchService;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected ISearchService getSearchService() {
        return supportAddressSearchService;
    }

    @Override
    protected JpaRepository<SupportAddress, Long> getRepository() {
        return supportAddressRepository;
    }

    @Override
    protected SupportAddressDto createDto() {
        return new SupportAddressDto();
    }

    @Override
    protected SupportAddress createEntity() {
        return new SupportAddress();
    }

    @Override
    protected void transform2D(SupportAddress entity, SupportAddressDto dto) {
        //设置上一行政级别的中文名
        if(StringUtils.isBlank(dto.getBelongToCnName())){
            dto.setBelongToCnName(supportAddressRepository.findOne(entity.getBelongTo()).getCnName());
        }
    }

    @Override
    protected void transform2E(SupportAddressDto dto, SupportAddress entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);
        }else{
            //新建
        }
    }

    @Override
    public List<SupportAddressDto> findAllRegionsByCityEnName(String cityEnName) {
        if (StringUtils.isBlank(cityEnName)) {
            return null;
        }

        SupportAddressDto city = findCity(cityEnName);

        return transform2D(supportAddressRepository.findAllByLevelAndBelongTo(Level.REGION.getCode(), city.getId()));
    }

    @Override
    public SupportAddressDto findCity(String cityEnName) {
        if (cityEnName == null) {
            return null;
        }

        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName, Level.CITY.getCode());
        if (supportAddress == null) {
            return null;
        }

        return transform2D(supportAddress);
    }

    @Override
    public Map<Level, SupportAddressDto> findCityAndRegion(Long cityId, Long regionId) {
        Map<Level, SupportAddressDto> result = new HashMap<>();

        SupportAddress city = supportAddressRepository.findOne(cityId);
        SupportAddress region = supportAddressRepository.findOne(regionId);

        result.put(Level.CITY, transform2D(city));
        result.put(Level.REGION, transform2D(region));
        return result;
    }

    @Override
    public List<SupportAddressDto> findByLevel(String level) {
        return transform2D(supportAddressRepository.findByLevel(level));
    }

    @Override
    public ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address) {
        String encodeAddress;
        String encodeCity;

        try {
            encodeAddress = URLEncoder.encode(address, "UTF-8");
            encodeCity = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Error to encode house address", e);
            return new ServiceResult<BaiduMapLocation>(false, "Error to encode hosue address");
        }

        HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder(BAIDU_MAP_GEOCONV_API);
        sb.append("address=").append(encodeAddress).append("&")
                .append("city=").append(encodeCity).append("&")
                .append("output=json&")
                .append("ak=").append(BAIDU_MAP_KEY);

        HttpGet get = new HttpGet(sb.toString());
        try {
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return new ServiceResult<BaiduMapLocation>(false, "Can not get baidu map location");
            }

            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                return new ServiceResult<BaiduMapLocation>(false, "Error to get map location for status: " + status);
            } {
                BaiduMapLocation location = new BaiduMapLocation();
                JsonNode jsonLocation = jsonNode.get("result").get("location");
                location.setLongitude(jsonLocation.get("lng").asDouble());
                location.setLatitude(jsonLocation.get("lat").asDouble());
                return ServiceResult.of(location);
            }

        } catch (IOException e) {
            logger.error("Error to fetch baidumap api", e);
            return new ServiceResult<BaiduMapLocation>(false, "Error to fetch baidumap api");
        }
    }
}
