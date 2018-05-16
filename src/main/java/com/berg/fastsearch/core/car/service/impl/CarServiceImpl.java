package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.service.*;
import com.berg.fastsearch.core.car.web.dto.*;
import com.berg.fastsearch.core.enums.car.*;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import org.apache.commons.collections.CollectionUtils;
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
     * 车辆repository对象
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

    /**
     * 地址服务
     */
    @Autowired
    private ISupportAddressService supportAddressService;

    /**
     * 车辆搜索服务
     */
    @Autowired
    private ICarSearchService carSearchService;

    /**
     * 图片的前缀
     */
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @Override
    protected ISearchService<Long, CarQueryCondition> getSearchService() {
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
        //设置品牌信息
        CarBrandDto brandDto = carBrandService.findOne(entity.getBrandId());
        //设置品牌名字
        dto.setBrand(brandDto.getName());
        //设置品牌代码
        dto.setBrandCode(brandDto.getCode());

        //设置系列信息
        CarSeriesDto seriesDto = carSeriesService.findOne(entity.getSeriesId());
        //设置系列名字
        dto.setSeries(seriesDto.getName());
        //设置系列代码
        dto.setSeriesCode(seriesDto.getCode());

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
        dto.setCityCnName(supportAddressService.findOne(entity.getCityId()).getCnName());
        //设置地区名
        dto.setRegionCnName(supportAddressService.findOne(entity.getRegionId()).getCnName());
    }

    @Override
    protected void transform2E(CarDto dto, Car entity) {
        //处理照片的数据
        List<CarPictureDto> carPictureDtos = dto.getPictures();
        if(CollectionUtils.isNotEmpty(carPictureDtos)){
            final Long carId = dto.getId();

            carPictureDtos.forEach(carPictureDto -> {
                Long id = carPictureDto.getId();
                if(id==null || id<1){
                    //新建
                    carPictureDto.setCarId(carId);
                    carPictureDto.setCdnPrefix(cdnPrefix);
                    carPictureService.create(carPictureDto);
                }else{
                    //更新
                    carPictureService.update(carPictureDto);
                }
            });
        }

        if(dto.getId()==null || dto.getId()<=0){
            //新建

            //处理创建时间
            entity.setCreateTime(new Date());

            entity.setDeployeeId(1L);
            entity.setStatus("NEW");
        }else{
            //更新
            dto = this.findOne(dto.getId());
            entity.setId(dto.getId());
            entity.setCover(dto.getCover());

            //查看次数
            Long watchTimes = entity.getWatchTimes();
            if(null==watchTimes || watchTimes<1){
                entity.setWatchTimes(dto.getWatchTimes());
            }

            //其他未展现的数据
            entity.setCreateTime(dto.getCreateTime());
            entity.setDeployeeId(dto.getDeployeeId());
            entity.setStatus(dto.getStatus());
        }

        //新建和更新都需要处理的
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());
    }

    @Override
    public CarDto watchCar(Long id) {
        CarDto carDto = this.findOne(id);

        //查看车辆数据增加一次
        carDto.setWatchTimes(carDto.getWatchTimes()+1);

        return this.update(carDto);
    }
}
