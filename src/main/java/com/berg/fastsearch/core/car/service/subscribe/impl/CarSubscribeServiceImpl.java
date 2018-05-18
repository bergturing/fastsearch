package com.berg.fastsearch.core.car.service.subscribe.impl;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.car.entity.CarSubscribe;
import com.berg.fastsearch.core.car.repository.CarSubscribeRepository;
import com.berg.fastsearch.core.car.service.subscribe.ICarSubscribeService;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.subscribe.CarSubscribeQueryCondition;
import com.berg.fastsearch.core.enums.car.SubscribeStatus;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import com.berg.fastsearch.core.utils.AccountUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
