package com.berg.fastsearch.core.address.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressSearchService;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressIndexMessage;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.address.web.dto.SupportAddressTemplate;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
@Service
public class SupportAddressSearchServiceImpl
        extends AbstractSearchService<Long, SupportAddressQueryCondition, SupportAddressIndexMessage, SupportAddressTemplate>
        implements ISupportAddressSearchService{

    @Autowired
    private ISupportAddressService supportAddressService;

    @Override
    protected SupportAddressTemplate map(Long id) {
        SupportAddressTemplate template = new SupportAddressTemplate();

        BeanUtils.copyProperties(supportAddressService.findOne(id), template);

        return template;
    }

    @Override
    protected Class<SupportAddressIndexMessage> getIndexMessageClass() {
        return SupportAddressIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, SupportAddressQueryCondition condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        //行政级别
        String level = condition.getLevel();
        if(StringUtils.isNotBlank(level)){
            builder = builder
                    .must(QueryBuilders.termQuery(SupportAddressQueryCondition.FIELD_LEVEL, level));
        }

        //所属地点
        Long belongTo = condition.getBelongTo();
        if(belongTo!=null && belongTo>0){
            builder = builder
                    .must(QueryBuilders.termQuery(SupportAddressQueryCondition.FIELD_BELONG_TO, belongTo));
        }

        //设置查询对象
        searchRequestBuilder.setQuery(builder);
    }

    @Override
    protected String getIndexName() {
        return "address";
    }

    @Override
    protected String getTypeName() {
        return "address";
    }
}
