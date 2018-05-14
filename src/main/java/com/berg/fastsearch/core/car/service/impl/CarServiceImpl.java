package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.service.*;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarPictureDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.enums.car.*;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
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
public class CarServiceImpl
        extends AbstractBaseServiceImpl<Long, CarDto, Car, CarQueryCondition>
        implements ICarService {

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

    /**
     * 汽车品牌服务对象
     */
    @Autowired
    private ICarBrandService carBrandService;

    /**
     * 汽车系列服务对象
     */
    @Autowired
    private ICarSeriesService carSeriesService;

    /**
     * 车辆标签的服务对象
     */
    @Autowired
    private ICarTagService carTagService;

    @Autowired
    private ICarSearchService carSearchService;

    @Autowired
    private ISupportAddressService supportAddressService;

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @Override
    protected ISearchService<Long> getSearchService() {
        return carSearchService;
    }

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
    protected void transform2D(Car entity, CarDto dto) {
        //设置品牌名字
        dto.setBrand(carBrandService.findOne(entity.getBrandId()).getName());
        //设置系列名字
        dto.setSeries(carSeriesService.findOne(entity.getSeriesId()).getName());
        //设置车辆标签
        dto.setTags(carTagService.findByCarId(entity.getId()));
        //设置车辆图片
        dto.setPictures(carPictureService.findByCarId(entity.getId()));

        //车辆颜色
        Color color = Color.get(entity.getColor());
        dto.setColorMeaning(color.getName());
        dto.setColorHex(color.getHex());
        //车辆驱动类型
        dto.setDriveTypeMeaning(DriveType.get(entity.getDriveType()).getName());
        //车辆排放标准
        dto.setEmissionStandardMeaning(EmissionStandard.get(entity.getEmissionStandard()).getName());
        //车辆燃油类型
        dto.setFuelTypeMeaning(FuelType.get(entity.getFuelType()).getName());
        //车辆变速箱类型
        dto.setGearBoxMeaning(GearBox.get(entity.getGearBox()).getName());
        //车辆类型
        dto.setStyleMeaning(Style.get(entity.getStyle()).getName());

        //设置城市名
        dto.setCityCnName(supportAddressService.findOne(entity.getCityId()).getEnName());
        //设置地区名
        dto.setRegionCnName(supportAddressService.findOne(entity.getRegionId()).getEnName());
    }

    @Override
    protected void transform2E(CarDto dto, Car entity) {
        //处理照片的数据
        List<CarPictureDto> carPictureDtos = dto.getPictures();
        carPictureDtos.forEach(carPictureDto -> {
            carPictureDto.setCarId(dto.getId());
            carPictureDto.setCdnPrefix(cdnPrefix);
            carPictureService.create(carPictureDto);
        });


        if(dto.getId()==null || dto.getId()<=0){
            //新建

            //处理创建时间
            entity.setCreateTime(new Date());

            entity.setDeployeeId(1L);
            entity.setStatus("NEW");
        }else{
            //更新

        }

        //新建和更新都需要处理的
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());
    }
}
