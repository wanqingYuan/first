package org.example.service;

import org.example.daomain.Permission;
import org.example.daomain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();
    public void save(Role role);
    public Role findById(int roleId);
    public List<Permission> findAllOtherPermission(int roleId);
    public void addRoleAndPermission(int roleId,Integer[] permissionIds);
}
