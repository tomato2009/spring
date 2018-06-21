package com.tomatoman.redis.service;

import com.tomatoman.redis.entity.Role;

public interface RoleService {
    Role selectByPrimaryKey(int id);
}
