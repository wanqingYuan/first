package org.example.service;

import org.example.dao.RoleDao;
import org.example.dao.UserDao;
import org.example.daomain.Role;
import org.example.daomain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo=userDao.findByUsername(s);
        List<Role> roles=userInfo.getRoles();
        List<SimpleGrantedAuthority> authorities=getAuthority(roles);
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,true,true,true,authorities);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (Role r :roles) {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        }
        return authorities;
    }

    public  List<UserInfo> findAll(){
        return userDao.findAll();
    }

    public void save(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findAllRoleEx(int userId) {
        return roleDao.findAllRoleEx(userId);
    }

    @Override
    public void addRoleToUser(int userId, Integer[] roleIds) {
        for (Integer roleId :roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }
}
