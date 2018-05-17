package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarSeries;
import com.berg.fastsearch.core.car.repository.CarSeriesRepository;
import com.berg.fastsearch.core.car.service.ICarBrandService;
import com.berg.fastsearch.core.car.service.ICarSeriesSearchSerivce;
import com.berg.fastsearch.core.car.service.ICarSeriesService;
import com.berg.fastsearch.core.car.web.dto.CarSeriesDto;
import com.berg.fastsearch.core.car.web.dto.CarSeriesQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Service
public class CarSeriesServiceImpl
        extends AbstractBaseServiceImpl<Long, CarSeriesDto, CarSeries, CarSeriesQueryCondition>
        implements ICarSeriesService {

    @Autowired
    private CarSeriesRepository carSeriesRepository;

    @Autowired
    private ICarSeriesSearchSerivce carSeriesSearchSerivce;

    @Autowired
    private ICarBrandService carBrandService;

    @Override
    protected ISearchService getSearchService() {
        return carSeriesSearchSerivce;
    }

    @Override
    protected JpaRepository<CarSeries, Long> getRepository() {
        return carSeriesRepository;
    }

    @Override
    protected CarSeriesDto createDto() {
        return new CarSeriesDto();
    }

    @Override
    protected CarSeries createEntity() {
        return new CarSeries();
    }

    @Override
    protected void transform2D(CarSeries entity, CarSeriesDto dto) {
        dto.setBrandName(carBrandService.findOne(entity.getBrandId()).getName());
    }

    @Override
    protected void transform2E(CarSeriesDto dto, CarSeries entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);
        }else{
            //新建
        }
    }
}
