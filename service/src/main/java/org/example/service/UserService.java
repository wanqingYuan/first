package org.example.service;

import org.example.daomain.Role;
import org.example.daomain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<UserInfo> findAll();
    public void save(UserInfo userInfo);
    public UserInfo findById(int id);
    public List<Role> findAllRoleEx(int userId);
    public void addRoleToUser(int userId,Integer[] roleIds);
}
