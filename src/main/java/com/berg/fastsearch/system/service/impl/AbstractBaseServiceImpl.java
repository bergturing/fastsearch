package com.berg.fastsearch.system.service.impl;

import com.berg.fastsearch.system.dto.BaseDto;
import com.berg.fastsearch.system.service.IBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public abstract class AbstractBaseServiceImpl<DTO extends BaseDto, ENTITY, ID extends Serializable> implements IBaseService<DTO, ENTITY> {
    @Override
    public List<DTO> findAll(){
        return transform2D(getRepository().findAll());
    }

    /**
     * 将entity转换成dto
     * @param entity    entity对象
     * @return          dto对象
     */
    protected final DTO transform2D(ENTITY entity){
        if(entity != null){
            DTO dto = createDto();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }else{
            return null;
        }
    }

    /**
     * 将entity列表转换成dto列表
     * @param entityList    entity列表
     * @return              dto列表
     */
    protected final List<DTO> transform2D(List<ENTITY> entityList){
        if(CollectionUtils.isEmpty(entityList)){
            return null;
        }else{
            List<DTO> dtoList = new ArrayList<>();

            entityList.forEach(entity -> {
                dtoList.add(transform2D(entity));
            });

            return dtoList;
        }
    }

    /**
     * 将dto转换成entity
     * @param dto       dto对象
     * @return          entity对象
     */
    protected final ENTITY transform2E(DTO dto){
        if(dto!=null){
            ENTITY entity = createEntity();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        }else{
            return null;
        }
    }

    /**
     * 将dto列表转换成entity列表
     * @param dtoList   dto列表
     * @return          entity列表
     */
    protected final List<ENTITY> transform2E(List<DTO> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return null;
        }else{
            List<ENTITY> entityList = new ArrayList<>();

            dtoList.forEach(dto -> {
                entityList.add(transform2E(dto));
            });

            return entityList;
        }
    }

    /**
     * 返回repository对象
     * @return  返回repository对象
     */
    protected abstract JpaRepository<ENTITY, ID> getRepository();

    /**
     * 返回DTO对象
     * @return  返回DTO对象
     */
    protected abstract DTO createDto();

    /**
     * 返回ENTITY对象
     * @return  返回ENTITY对象
     */
    protected abstract ENTITY createEntity();
}
