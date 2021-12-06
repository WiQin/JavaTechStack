package com.wyw.mybatis.service;

import com.wyw.mybatis.entity.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RoleServiceTest {

    @Test
    public void test() {
        Role role = new Role();
        role.setRoleName("Boss2");
        role.setNotes("老板");

        RoleService roleService = new RoleService();
//        roleService.insertRole(role);
//        System.out.println(role.getId());
        List<Role> boss = roleService.getRoles("Boss");
        System.out.println(boss.size());
    }

}