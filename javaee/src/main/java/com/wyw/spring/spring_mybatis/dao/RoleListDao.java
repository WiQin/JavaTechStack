package com.wyw.spring.spring_mybatis.dao;

import com.wyw.mybatis.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/03
 */
@Repository
public interface RoleListDao {
    int insertRoleList(List<Role> roleList);
}
