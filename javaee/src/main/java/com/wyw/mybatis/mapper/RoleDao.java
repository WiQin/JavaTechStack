package com.wyw.mybatis.mapper;

import com.wyw.mybatis.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    boolean insertRole(Role role);
    boolean deleteRole(Long id);
    boolean updateRole(Role role);
    Role getRole(Long id);
    List<Role> findRoles(String roleName);
    List<Role> findRolesByNameAndNotes(@Param("roleName") String roleName, @Param("note") String note);


}
