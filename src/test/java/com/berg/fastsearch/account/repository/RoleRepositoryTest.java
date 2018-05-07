package com.berg.fastsearch.account.repository;

import com.berg.fastsearch.FastsearchApplicationTests;
import com.berg.fastsearch.core.account.entity.Role;
import com.berg.fastsearch.core.account.repository.RoleRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleRepositoryTest extends FastsearchApplicationTests{
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findAllTest(){
        List<Role> all = roleRepository.findAll();

        System.out.println(all);
    }
}