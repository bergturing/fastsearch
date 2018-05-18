package com.berg.fastsearch.core.system.base.service.impl;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.dto.BaseDto;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.base.entity.ServiceMultiResult;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

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
public abstract class AbstractBaseServiceImpl<
        ID extends Serializable,
        DTO extends BaseDto<ID>,
        ENTITY extends BaseEntity,
        CONDITION extends BaseQueryCondition> implements IBaseService<ID, DTO, CONDITION> {

    @Override
    public final ServiceMultiResult<DTO> findAll(CONDITION condition){
        if(null == condition){
            List<DTO> dtoList = transform2D(getRepository().findAll());
            return new ServiceMultiResult<DTO>(dtoList.size(), dtoList);
        }

        if(getSearchService()!=null){
            //获取条件查询的ids
            ServiceMultiResult<ID> multiResult = getSearchService().query(condition);

            //查询数据
            return new ServiceMultiResult<DTO>(multiResult.getTotal(), transform2D(getRepository().findAll(multiResult.getResult())));
        }else{
            //查询数据
            List<DTO> dtoList = transform2D(getRepository().findAll());
            return new ServiceMultiResult<DTO>(dtoList.size(), dtoList);
        }
    }

    @Override
    public final DTO findOne(ID id) {
        return transform2D(getRepository().findOne(id));
    }

    @Override
    public final DTO create(DTO dto) throws Exception {
        //创建数据
        ID id = transform2D(getRepository().save(transform2E(dto))).getId();

        //数据添加成功之后的操作
        afterCreate(id, dto);

        //建立索引
        if(getSearchService()!=null){
            getSearchService().index(id);
        }

        //返回结果
        return this.findOne(id);
    }

    @Override
    public final DTO update(DTO dto) throws Exception {
        //更新数据
        dto = transform2D(getRepository().save(transform2E(dto)));

        //建立索引
        if(getSearchService()!=null){
            getSearchService().index(dto.getId());
        }

        //返回结果
        return dto;
    }

    @Override
    public final DTO delete(ID id) {
        //查找删除
        DTO dto = this.findOne(id);

        //删除数据
        getRepository().delete(id);

        //删除索引
        if(getSearchService()!=null){
            getSearchService().remove(dto.getId());
        }

        //返回删除的数据
        return dto;
    }

    @Override
    public final List<DTO> batchDelete(List<ID> ids) {
        final List<DTO> dtoList = new ArrayList<>();

        //判断是否有删除的对象
        if(CollectionUtils.isNotEmpty(ids)){
            //删除数据的处理
            ids.forEach(id -> {
                dtoList.add(this.delete(id));
            });
        }

        //返回处理结果
        return dtoList;
    }

    @Override
    public final boolean indexAll() {
        List<DTO> all = this.findAll(null).getResult();

        final ISearchService searchService = getSearchService();
        if(searchService != null){
            if(CollectionUtils.isNotEmpty(all)){
                all.forEach(dto -> {
                    searchService.index(dto.getId());
                });
            }
        }

        return true;
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

            transform2D(entity, dto);

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
        List<DTO> dtoList = new ArrayList<>();

        //不为空才执行
        if(CollectionUtils.isNotEmpty(entityList)){
            entityList.forEach(entity -> {
                dtoList.add(transform2D(entity));
            });
        }

        return dtoList;
    }

    /**
     * 将dto转换成entity
     * @param dto       dto对象
     * @return          entity对象
     */
    protected final ENTITY transform2E(DTO dto) throws Exception {
        if(dto!=null){
            ENTITY entity = createEntity();
            BeanUtils.copyProperties(dto, entity);

            transform2E(dto, entity);

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
                try {
                    entityList.add(transform2E(dto));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return entityList;
        }
    }

    protected ISearchService getSearchService(){
        return null;
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

    /**
     * entity转换成dto的额外操作
     * @param entity    entity对象
     * @param dto       dto对象
     */
    protected void transform2D(final ENTITY entity, final DTO dto){ }

    /**
     * dto转换成entity的额外操作
     * @param dto       dto对象
     * @param entity    entity对象
     */
    protected void transform2E(final DTO dto, final ENTITY entity) throws Exception { }

    /**
     * 存储之后的操作
     * @param id    主键
     * @param dto   操作之后的dto
     */
    protected void afterCreate(ID id, DTO dto) throws Exception {}

}
