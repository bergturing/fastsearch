package com.berg.fastsearch.core.account.service.impl;

import com.berg.fastsearch.core.account.web.dto.UserQueryCondition;
import com.berg.fastsearch.core.account.web.dto.UserRoleDto;
import com.berg.fastsearch.core.account.entity.UserRole;
import com.berg.fastsearch.core.account.repository.UserRoleRepository;
import com.berg.fastsearch.core.account.service.IUserRoleService;
import com.berg.fastsearch.core.account.web.dto.UserRoleQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>用户角色服务实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class UserRoleServiceImpl
        extends AbstractBaseServiceImpl<Long, UserRoleDto, UserRole, UserRoleQueryCondition>
        implements IUserRoleService {
    /**
     * 角色服务
     */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleDto> findByUserId(Long userId) {
        return transform2D(userRoleRepository.findByUserId(userId));
    }

    @Override
    protected JpaRepository<UserRole, Long> getRepository() {
        return userRoleRepository;
    }

    @Override
    protected UserRoleDto createDto() {
        return new UserRoleDto();
    }

    @Override
    protected UserRole createEntity() {
        return new UserRole();
    }

    @Override
    protected void transform2E(UserRoleDto dto, UserRole entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);
        }else{
            //新建
        }
    }
}
