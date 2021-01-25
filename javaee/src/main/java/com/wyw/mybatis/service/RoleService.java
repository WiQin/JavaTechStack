package com.wyw.mybatis.service;

import com.wyw.mybatis.entity.Role;
import com.wyw.mybatis.mapper.RoleDao;
import com.wyw.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoleService {

    SqlSession session = null;

    private RoleDao getMapper() {
        try {
            session = SqlSessionFactoryUtil.openSession();
            RoleDao roleDao = session.getMapper(RoleDao.class);
            return roleDao;
        } catch (Exception e) {
            e.printStackTrace();
        } //finally {
//            if (session != null) {
//                session.close();
//            }
//        }
        return null;
    }

    public void insertRole(Role role) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            roleDao.insertRole(role);
            session.commit();
        }
    }

    public void deleteRole(Long id) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            roleDao.deleteRole(id);
        }
    }

    public void updateRole(Role role) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            roleDao.updateRole(role);
        }
    }

    public Role getRole(Long id) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            return roleDao.getRole(id);
        }
        return null;
    }

    public List<Role> getRoles(String roleName) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            return roleDao.findRoles(roleName);
        }
        return new ArrayList<Role>();
    }

    public List<Role> getRolesByNameAndNote(String name, String note) {
        RoleDao roleDao = getMapper();
        if (null != roleDao) {
            return roleDao.findRolesByNameAndNotes(name,note);
        }
        return new ArrayList<Role>();
    }



}
