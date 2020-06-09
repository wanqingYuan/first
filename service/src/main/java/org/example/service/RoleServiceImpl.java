package org.example.service;

import org.example.dao.RoleDao;
import org.example.daomain.Permission;
import org.example.daomain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(int roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findAllOtherPermission(int roleId) {
        return roleDao.findAllOtherPermission(roleId);
    }

    @Override
    public void addRoleAndPermission(int roleId, Integer[] permissionIds) {
        for (Integer permissionId :permissionIds) {
            roleDao.addRoleAndPermission(roleId,permissionId);
        }
    }
}
