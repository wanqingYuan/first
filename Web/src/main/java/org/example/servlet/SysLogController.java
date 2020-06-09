package org.example.servlet;

import org.example.daomain.SysLog;
import org.example.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<SysLog> list=sysLogService.findAll();
        model.addObject("sysLogs",list);
        model.setViewName("syslog-list");
        return model;
    }
}
