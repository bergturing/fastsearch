package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.service.ICarPictureService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarPictureDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.car.web.dto.Photo;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Service
public class CarServiceImpl extends AbstractBaseServiceImpl<Long, CarDto, Car, CarQueryCondition> implements ICarService {

    /**
     *
     */
    @Autowired
    private CarRepository carRepository;

    /**
     * 车辆图片服务对象
     */
    @Autowired
    private ICarPictureService carPictureService;

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @Override
    protected JpaRepository<Car, Long> getRepository() {
        return carRepository;
    }

    @Override
    protected CarDto createDto() {
        return new CarDto();
    }

    @Override
    protected Car createEntity() {
        return new Car();
    }

    @Override
    protected void processDto(CarDto dto) {
        //处理照片的数据
        List<Photo> photos = dto.getPhotos();

        photos.forEach(photo -> {
            CarPictureDto carPictureDto = new CarPictureDto();
            carPictureDto.setCarId(dto.getId());
            carPictureDto.setPath(photo.getPath());
            carPictureDto.setHeight(photo.getHeight());
            carPictureDto.setWidth(photo.getWidth());
            carPictureDto.setCdnPrefix(cdnPrefix);
            carPictureService.create(carPictureDto);
        });
    }

    @Override
    protected void processEntity(Car entity) {
        //处理创建时间
        entity.setCreateTime(new Date());
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());

        entity.setCityEnName("bj");
        entity.setRegionEnName("cpq");
        entity.setDeployeeId(1L);
        entity.setStatus("NEW");
    }

    @Override
    protected void updateDto(CarDto dto) {
        super.updateDto(dto);
    }

    @Override
    protected void updateEntity(Car entity) {
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());
    }
}
