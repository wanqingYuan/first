package org.example.dao;

import com.sun.tools.javac.comp.Resolve;
import org.apache.ibatis.annotations.Param;
import org.example.daomain.UserInfo;

import java.util.List;

public interface UserDao {

    public UserInfo findById(int id);
    public UserInfo findByUsername(String username);
    public List<UserInfo> findAll();
    public void save(UserInfo userInfo);
    public void addRoleToUser(@Param("userId") int userId,@Param("roleId") int roleId);
}
