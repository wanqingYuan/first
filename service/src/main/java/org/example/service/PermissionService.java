package org.example.service;

import org.example.daomain.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findAll();
    public void save(Permission permission);
}
