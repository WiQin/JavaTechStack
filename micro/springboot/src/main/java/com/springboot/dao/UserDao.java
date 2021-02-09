package com.springboot.dao;

import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/09
 */
@Mapper
public interface UserDao {

    void insertUser(User user);
}
