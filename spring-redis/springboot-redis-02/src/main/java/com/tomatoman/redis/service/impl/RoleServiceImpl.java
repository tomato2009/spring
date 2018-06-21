package com.tomatoman.redis.service.impl;

import com.tomatoman.redis.entity.Role;
import com.tomatoman.redis.mapper.RoleMapper;
import com.tomatoman.redis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
