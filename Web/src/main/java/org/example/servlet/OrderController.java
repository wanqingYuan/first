package org.example.servlet;

import com.github.pagehelper.PageInfo;
import org.example.daomain.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll(){
//        ModelAndView model=new ModelAndView();
//        List<Order> list=orderService.findAll();
//        model.addObject("ordersList",list);
//        model.setViewName("orders-list");
//        return model;
//    }

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size,
                                @RequestParam(name = "orderId",required = true,defaultValue = "")String orderId){
        ModelAndView model=new ModelAndView();
        List<Order> list=orderService.findAllByPage(page, size,orderId);
        list.get(0).setSearch(orderId);
        PageInfo pageInfo=new PageInfo(list);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("orders-page-list");
        return model;
    }
    @RequestMapping("/findById.do")
    @PermitAll
    public ModelAndView findById(@RequestParam(name = "id",required = true) int id){
        ModelAndView model=new ModelAndView();
        Order order=orderService.findById(id);
        model.addObject("orders",order);
        model.setViewName("orders-show");
        return model;
    }
    @RequestMapping("/delete.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String delete(@RequestParam(name = "ids",required = true) Integer[] ids){
        System.out.println(ids);
        orderService.delete(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }
    @RequestMapping("/open.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String open(@RequestParam(name = "ids",required = true) Integer[] ids){
        orderService.open(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }
    @RequestMapping("/close.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String close(@RequestParam(name = "ids",required = true) Integer[] ids){
        orderService.close(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }
}
