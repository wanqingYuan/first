package org.example.servlet;

import org.apache.ibatis.annotations.Param;
import org.example.daomain.Role;
import org.example.daomain.UserInfo;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<UserInfo> list=userService.findAll();
        model.addObject("userList",list);
        model.setViewName("user-list");
        return model;
    }

    @RequestMapping("/save.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String  save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ModelAndView findById(int id){
        ModelAndView model=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        model.addObject("user",userInfo);
        model.setViewName("user-show");
        return model;
    }
    @RequestMapping("/findUserByIdAndAllRole.do")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ModelAndView findUserByIdAndAllRole(int id){
        ModelAndView model=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        List<Role> list=userService.findAllRoleEx(id);
        model.addObject("user",userInfo);
        model.addObject("roleList",list);
        model.setViewName("user-role-add");
        return model;
    }
    @RequestMapping("/addRoleToUser.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String addRoleToUser(@RequestParam(name = "userId",required = true) int userId,
                                @RequestParam(name = "ids",required = true) Integer[] roleIds){
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
