package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.entity.CarTagAss;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.service.*;
import com.berg.fastsearch.core.car.web.dto.*;
import com.berg.fastsearch.core.enums.car.*;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.berg.fastsearch.core.utils.AccountUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
     * 车辆预约的服务
     */
    @Autowired
    private ICarSubscribeService subscribeService;

    /**
     * 用户服务
     */
    @Autowired
    private IUserService userService;

    /**
     * 汽车标签分派服务
     */
    @Autowired
    private ICarTagAssService carTagAssService;

    /**
     * 图片的前缀
     */
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @Override
    protected ISearchService getSearchService() {
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

        //设置预约对象
        CarSubscribeQueryCondition carSubscribeQueryCondition = new CarSubscribeQueryCondition();
        carSubscribeQueryCondition.setCarId(entity.getId());
        carSubscribeQueryCondition.setUserId(1L);
        List<CarSubscribeDto> carSubscribeDtoList = subscribeService.findAll(carSubscribeQueryCondition);
        if(CollectionUtils.isNotEmpty(carSubscribeDtoList) && carSubscribeDtoList.size()==1){
            dto.setSubscribe(carSubscribeDtoList.get(0));
        }else {
            dto.setSubscribe(new CarSubscribeDto());
        }

    }

    @Override
    protected void transform2E(CarDto dto, Car entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);

            //处理其他数据
            dto = this.findOne(id);
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
        }else{
            //新建

            //处理创建时间
            entity.setCreateTime(new Date());

            String loginUserName = AccountUtil.getLoginUserName();
            if(StringUtils.isNotBlank(loginUserName)){
                UserDto userDto = userService.findByName(loginUserName);
                if(userDto != null){
                    //设置部署人
                    entity.setDeployeeId(userDto.getId());
                    //设置封面
                    entity.setCover(cdnPrefix + dto.getCover());
                    //设置车辆状态为新建
                    entity.setStatus(Status.NEW.getCode());
                    //设置车辆的初始查看次数
                    entity.setWatchTimes(0L);
                }else{
                    throw new Exception("用户不存在");
                }
            }else{
                throw new Exception("用户未登录");
            }
        }

        //新建和更新都需要处理的
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());
    }

    @Override
    protected void afterCreate(Long id, CarDto dto) throws Exception {
        //处理照片的数据
        List<CarPictureDto> carPictureDtos = dto.getPictures();
        if(CollectionUtils.isNotEmpty(carPictureDtos)){
            for (CarPictureDto carPictureDto : carPictureDtos) {
                Long pictureDtoId = carPictureDto.getId();
                if(pictureDtoId==null || pictureDtoId<1){
                    //新建
                    carPictureDto.setCarId(id);
                    carPictureDto.setCdnPrefix(cdnPrefix);
                    carPictureService.create(carPictureDto);
                }else{
                    //更新
                    carPictureService.update(carPictureDto);
                }
            }
        }

        //处理标签
        List<CarTagDto> tags = dto.getTags();
        if(CollectionUtils.isNotEmpty(tags)){
            CarTagAssDto tagAss = new CarTagAssDto();
            for (CarTagDto tag : tags) {
                Long tagId = tag.getId();
                if(tagId!=null && tagId>0){
                    tagAss.setCarId(id);
                    tagAss.setCarTagId(tagId);
                    carTagAssService.create(tagAss);
                }
            }
        }
    }

    @Override
    public CarDto watchCar(Long id) throws Exception {
        CarDto carDto = this.findOne(id);

        //查看车辆数据增加一次
        carDto.setWatchTimes(carDto.getWatchTimes()+1);

        return this.update(carDto);
    }
}
