package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.daomain.Permission;
import org.example.daomain.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> findAll();
    public List<Role> findRoleByUserId(int userId);
    public void save(Role role);
    public List<Role> findAllRoleEx(int userId);
    public Role findById(int roleId);

    public List<Permission> findAllOtherPermission(int roleId);
    public void addRoleAndPermission(@Param("roleId") int roleId,@Param("permissionId") int permissionId);
}
