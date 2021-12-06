package com.wyw.spring;

import com.wyw.mybatis.entity.Role;
import com.wyw.spring.spring_mybatis.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/02/03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @Test
    public void test() {
        Role role = new Role();
        role.setRoleName("testRepository");
        role.setNotes("test");

        roleService.addRole(role);
    }
}
