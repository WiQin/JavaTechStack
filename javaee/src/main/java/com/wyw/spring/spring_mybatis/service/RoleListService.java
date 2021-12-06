package com.wyw.spring.spring_mybatis.service;

import com.wyw.mybatis.entity.Role;

import java.util.List;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/03
 */
public interface RoleListService {
    int addRoleList(List<Role> roleList);
}
