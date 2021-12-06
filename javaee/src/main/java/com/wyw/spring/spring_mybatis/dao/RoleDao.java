package com.wyw.spring.spring_mybatis.dao;

import com.wyw.mybatis.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/03
 */
@Repository
public interface RoleDao {
    int insertDao(Role role);
}
