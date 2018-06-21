package com.tomatoman.redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Role implements Serializable{
    private static final long serialVersionUID = -694241661363721688L;
    private Integer id;
    private String roleName;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}