package org.example.dao;

import org.example.daomain.Permission;

import java.util.List;

public interface PermissionDao {

    public List<Permission> findByRoleId(int roleId);

    public List<Permission> findAll();
    public void save(Permission permission);
}
