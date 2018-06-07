package com.berg.fastsearch.core.car.service.subscribe.impl;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.repository.SupportAddressRepository;
import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.entity.CarBrand;
import com.berg.fastsearch.core.car.entity.CarSubscribe;
import com.berg.fastsearch.core.car.repository.CarBrandRepository;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.repository.CarSeriesRepository;
import com.berg.fastsearch.core.car.repository.CarSubscribeRepository;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.service.subscribe.ICarSubscribeService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeQueryCondition;
import com.berg.fastsearch.core.car.web.dto.subscribe.SubscribeData;
import com.berg.fastsearch.core.enums.car.Status;
import com.berg.fastsearch.core.enums.car.SubscribeStatus;
import com.berg.fastsearch.core.system.base.entity.ServiceMultiResult;
import com.berg.fastsearch.core.system.base.entity.ServiceResult;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.berg.fastsearch.core.utils.AccountUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Service
public class CarSubscribeServiceImpl
        extends AbstractBaseServiceImpl<Long, CarSubscribeDto, CarSubscribe, CarSubscribeQueryCondition>
        implements ICarSubscribeService{

    /**
     * 车辆预约色repository对象
     */
    @Autowired
    private CarSubscribeRepository carSubscribeRepository;

    /**
     * 用户的服务对象
     */
    @Autowired
    private IUserService userService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICarService carService;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarSeriesRepository carSeriesRepository;

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Override
    protected JpaRepository<CarSubscribe, Long> getRepository() {
        return carSubscribeRepository;
    }

    @Override
    protected CarSubscribeDto createDto() {
        return new CarSubscribeDto();
    }

    @Override
    protected CarSubscribe createEntity() {
        return new CarSubscribe();
    }

    @Override
    protected void transform2D(CarSubscribe entity, CarSubscribeDto dto) {
        //车辆信息
        Car car = carRepository.findOne(entity.getCarId());
        dto.setTitle(car.getTitle());
        dto.setBrand(carBrandRepository.findOne(car.getBrandId()).getName());
        dto.setSeries(carSeriesRepository.findOne(car.getSeriesId()).getName());
        dto.setCity(supportAddressRepository.findOne(car.getCityId()).getCnName());
        dto.setRegion(supportAddressRepository.findOne(car.getRegionId()).getCnName());

        //用户信息
        UserDto user = userService.findOne(entity.getUserId());
        dto.setUserName(user.getName());

        //预约状态含义
        dto.setStatusMeaning(SubscribeStatus.valueOf(entity.getStatus()).getName());
    }

    @Override
    protected void transform2E(CarSubscribeDto dto, CarSubscribe entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);
        }else{
            //新建
            entity.setCreateTime(new Date());
            entity.setStatus(SubscribeStatus.ADDED.getCode());

            //设置当前用户
            String loginUserName = AccountUtil.getLoginUserName();
            if(StringUtils.isNotBlank(loginUserName)){
                UserDto userDto = userService.findByName(loginUserName);
                if(userDto != null){
                    entity.setUserId(userDto.getId());
                }else{
                    throw new Exception("用户不存在");
                }
            }else{
                throw new Exception("用户未登录");
            }
        }

        entity.setLastUpdateTime(new Date());
    }

    @Override
    public CarSubscribeDto findByCarIdAndUserId(Long carId, Long userId) {
        return transform2D(carSubscribeRepository.findByCarIdAndUserId(carId, userId));
    }

    @Override
    public ServiceMultiResult<CarSubscribeDto> findMe(String status) {
        List<CarSubscribe> byUserId = carSubscribeRepository.findByUserIdAndStatus(userService.findByName(AccountUtil.getLoginUserName()).getId(), status);

        return new ServiceMultiResult<CarSubscribeDto>(byUserId.size(), transform2D(byUserId));
    }

    @Override
    public ServiceResult<CarSubscribeDto> del(Long id) {
        CarSubscribe one = carSubscribeRepository.findOne(id);

        if(SubscribeStatus.SUBSCRIBED.getCode().equals(one.getStatus())){
            one.setStatus(SubscribeStatus.ADDED.getCode());
            carSubscribeRepository.save(one);
            return new ServiceResult<>(true);
        }else{
            return new ServiceResult<>(false);
        }
    }

    @Override
    public ServiceResult<CarSubscribeDto> subscribe(SubscribeData subscribeData) {
        if(subscribeData!=null){
            List<Long> ids = subscribeData.getIds();

            if(CollectionUtils.isNotEmpty(ids)){
                CarSubscribe carSubscribe = null;

                for (Long id : ids) {
                    carSubscribe = carSubscribeRepository.findOne(id);
                    if(SubscribeStatus.ADDED.getCode().equals(carSubscribe.getStatus())){
                        carSubscribe.setStatus(SubscribeStatus.SUBSCRIBED.getCode());
                        carSubscribe.setPhoneNumber(subscribeData.getTelephone());
                        carSubscribe.setOrderTime(subscribeData.getOrderTime());
                        carSubscribeRepository.save(carSubscribe);
                        ISearchService searchService = getSearchService();
                        if(searchService != null){
                            searchService.index(carSubscribe.getCarId());
                        }
                    }else{
                        return new ServiceResult<CarSubscribeDto>(false);
                    }
                }

                return new ServiceResult<CarSubscribeDto>(true);
            }
        }

        return new ServiceResult<CarSubscribeDto>(false);
    }

    @Override
    public ServiceResult<CarSubscribeDto> finishSaw(Long id) {
        CarSubscribe carSubscribe = carSubscribeRepository.findOne(id);

        if(SubscribeStatus.SUBSCRIBED.getCode().equals(carSubscribe.getStatus())){
            carSubscribe.setStatus(SubscribeStatus.SAW.getCode());
            carSubscribeRepository.save(carSubscribe);
            ISearchService searchService = getSearchService();
            if(searchService != null){
                searchService.index(carSubscribe.getCarId());
            }
            return new ServiceResult<>(true);
        }else{
            return new ServiceResult<>(false);
        }
    }

    @Override
    public ServiceResult<CarSubscribeDto> finishTrade(Long id) throws Exception {
        CarSubscribe carSubscribe = carSubscribeRepository.findOne(id);

        if(SubscribeStatus.SUBSCRIBED.getCode().equals(carSubscribe.getStatus())){
            carSubscribe.setStatus(SubscribeStatus.SAW.getCode());
            carSubscribeRepository.save(carSubscribe);
            ISearchService searchService = getSearchService();
            if(searchService != null){
                searchService.index(carSubscribe.getCarId());
            }

            CarDto car = carService.findOne(carSubscribe.getCarId());
            car.setStatus(Status.SOLDED.getCode());
            carService.update(car);

            return new ServiceResult<>(true);
        }else{
            return new ServiceResult<>(false);
        }
    }
}
