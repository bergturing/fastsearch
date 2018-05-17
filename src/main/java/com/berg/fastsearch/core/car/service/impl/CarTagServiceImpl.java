package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarTag;
import com.berg.fastsearch.core.car.repository.CarTagRepository;
import com.berg.fastsearch.core.car.service.ICarTagAssService;
import com.berg.fastsearch.core.car.service.ICarTagSearchService;
import com.berg.fastsearch.core.car.service.ICarTagService;
import com.berg.fastsearch.core.car.web.dto.CarTagAssDto;
import com.berg.fastsearch.core.car.web.dto.CarTagDto;
import com.berg.fastsearch.core.car.web.dto.CarTagQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Service
public class CarTagServiceImpl
        extends AbstractBaseServiceImpl<Long, CarTagDto, CarTag, CarTagQueryCondition>
        implements ICarTagService{

    /**
     * 车辆标签的repository对象
     */
    @Autowired
    private CarTagRepository carTagRepository;

    /**
     * 车辆与标签关联关系的服务对象
     */
    @Autowired
    private ICarTagAssService carTagAssService;

    /**
     *
     */
    @Autowired
    private ICarTagSearchService carTagSearchService;

    @Override
    protected ISearchService getSearchService() {
        return carTagSearchService;
    }

    @Override
    protected JpaRepository<CarTag, Long> getRepository() {
        return carTagRepository;
    }

    @Override
    protected CarTagDto createDto() {
        return new CarTagDto();
    }

    @Override
    protected CarTag createEntity() {
        return new CarTag();
    }

    @Override
    protected void transform2E(CarTagDto dto, CarTag entity) throws Exception {
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
    public List<CarTagDto> findByCarId(Long carId) {
        List<CarTagAssDto> carTagAssDtos = carTagAssService.findByCarId(carId);

        List<CarTagDto> carTagDtos = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(carTagAssDtos)){
            //查找tag对象
            carTagAssDtos.forEach(carTagAssDto -> {
                carTagDtos.add(transform2D(carTagRepository.findOne(carTagAssDto.getCarTagId())));
            });
        }

        return carTagDtos;
    }
}
