package com.wyw.spring.spring_mybatis.service.impl;

import com.wyw.mybatis.entity.Role;
import com.wyw.spring.spring_mybatis.dao.RoleDao;
import com.wyw.spring.spring_mybatis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/03
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    public int addRole(Role role) {
        return roleDao.insertDao(role);
    }
}
