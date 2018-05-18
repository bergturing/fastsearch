package com.berg.fastsearch.core.car.service.tag.impl;

import com.berg.fastsearch.core.car.entity.CarTagAss;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.repository.CarTagAssRepository;
import com.berg.fastsearch.core.car.repository.CarTagRepository;
import com.berg.fastsearch.core.car.service.tag.ICarTagAssSearchService;
import com.berg.fastsearch.core.car.service.tag.ICarTagAssService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssDto;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Service
public class CarTagAssServiceImpl
        extends AbstractBaseServiceImpl<Long, CarTagAssDto, CarTagAss, CarTagAssQueryCondition>
        implements ICarTagAssService{

    @Autowired
    private CarTagAssRepository carTagAssRepository;

    @Autowired
    private ICarTagAssSearchService carTagAssSearchService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarTagRepository carTagRepository;

    @Override
    protected ISearchService getSearchService() {
        return carTagAssSearchService;
    }

    @Override
    protected JpaRepository<CarTagAss, Long> getRepository() {
        return carTagAssRepository;
    }

    @Override
    protected CarTagAssDto createDto() {
        return new CarTagAssDto();
    }

    @Override
    protected CarTagAss createEntity() {
        return new CarTagAss();
    }

    @Override
    protected void transform2D(CarTagAss entity, CarTagAssDto dto) {
        dto.setCarTagName(carTagRepository.findOne(entity.getCarTagId()).getName());
        dto.setCarTitle(carRepository.findOne(entity.getCarId()).getTitle());
    }

    @Override
    protected void transform2E(CarTagAssDto dto, CarTagAss entity) throws Exception {
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
    public List<CarTagAssDto> findByCarId(Long carId) {
        return transform2D(carTagAssRepository.findByCarId(carId));
    }
}
