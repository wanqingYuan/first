package org.example.servlet;


import org.example.daomain.Permission;
import org.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    @PermitAll
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<Permission> list=permissionService.findAll();
        model.addObject("permissionList",list);
        model.setViewName("permission-list");
        return model;
    }
    @RequestMapping("/save.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
