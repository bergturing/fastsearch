package com.berg.fastsearch.core.address.service.impl;

import com.berg.fastsearch.core.address.entity.SupportAddress;
import com.berg.fastsearch.core.address.repository.SupportAddressRepository;
import com.berg.fastsearch.core.address.service.ISupportAddressService;
import com.berg.fastsearch.core.address.web.dto.SupportAddressDto;
import com.berg.fastsearch.core.address.web.dto.SupportAddressQueryCondition;
import com.berg.fastsearch.core.enums.address.Level;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
@Service
public class SupportAddressServiceImpl
        extends AbstractBaseServiceImpl<Long, SupportAddressDto, SupportAddress, SupportAddressQueryCondition>
        implements ISupportAddressService{

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Override
    protected JpaRepository<SupportAddress, Long> getRepository() {
        return supportAddressRepository;
    }

    @Override
    protected SupportAddressDto createDto() {
        return new SupportAddressDto();
    }

    @Override
    protected SupportAddress createEntity() {
        return new SupportAddress();
    }

    @Override
    public List<SupportAddressDto> findAllRegionsByCityName(String cityName) {
        if (cityName == null) {
            return null;
        }

        List<SupportAddress> regions = supportAddressRepository.findAllByLevelAndBelongTo(Level.REGION
                .getValue(), cityName);

        return transform2D(supportAddressRepository.findAllByLevelAndBelongTo(Level.REGION
                .getValue(), cityName));
    }

    @Override
    public SupportAddressDto findCity(String cityEnName) {
        if (cityEnName == null) {
            return null;
        }

        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName, Level.CITY.getValue());
        if (supportAddress == null) {
            return null;
        }

        return transform2D(supportAddress);
    }

    @Override
    public Map<Level, SupportAddressDto> findCityAndRegion(Long cityId, Long regionId) {
        Map<Level, SupportAddressDto> result = new HashMap<>();

        SupportAddress city = supportAddressRepository.findOne(cityId);
        SupportAddress region = supportAddressRepository.findOne(regionId);

        result.put(Level.CITY, transform2D(city));
        result.put(Level.REGION, transform2D(region));
        return result;
    }

    @Override
    public List<SupportAddressDto> findByLevel(String level) {
        return transform2D(supportAddressRepository.findByLevel(level));
    }
}
