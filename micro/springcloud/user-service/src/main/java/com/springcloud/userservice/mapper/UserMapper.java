package com.springcloud.userservice.mapper;

import com.springcloud.userservice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/29
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

}
