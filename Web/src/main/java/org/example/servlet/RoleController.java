package org.example.servlet;

import org.example.daomain.Permission;
import org.example.daomain.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAll.do")
    @PermitAll
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<Role> list=roleService.findAll();
        model.addObject("roleList",list);
        model.setViewName("role-list");
        return model;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAllOtherPermission.do")
    public ModelAndView findAllOtherPermission(@RequestParam(name = "id",required = true) int roleId){
        ModelAndView model=new ModelAndView();
        Role role=roleService.findById(roleId);
        List<Permission> list=roleService.findAllOtherPermission(roleId);
        model.addObject("role",role);
        model.addObject("permissionList",list);
        model.setViewName("role-permission-add");
        return model;
    }

    @RequestMapping("/addRoleAndPermission.do")
    public String addRoleAndPermission(@RequestParam(name = "roleId",required = true) int roleId,
                                       @RequestParam(name = "ids",required = true) Integer[] permissionIdS){
        roleService.addRoleAndPermission(roleId, permissionIdS);
        return "redirect:findAll.do";
    }
}
