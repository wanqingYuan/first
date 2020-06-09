package org.example.servlet;

import com.github.pagehelper.PageInfo;
import org.example.daomain.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer pageSize){
        ModelAndView model=new ModelAndView();
        List<Product> productList=productService.findAll(page,pageSize);
        PageInfo pageInfo=new PageInfo(productList);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("product-list");
        return model;
    }
    @RequestMapping("/save.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";//重定向到findAll方法
    }
    @RequestMapping("/delete.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String delete(@RequestParam(name = "ids",required = true) Integer[] ids){
        System.out.println(ids);
        productService.delete(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }
    @RequestMapping("/open.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String open(@RequestParam(name = "ids",required = true) Integer[] ids){
        System.out.println(ids);
        productService.delete(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }
    @RequestMapping("/close.do")
    @RolesAllowed({"ROLE_ADMIN"})
    public String close(@RequestParam(name = "ids",required = true) Integer[] ids){
        System.out.println(ids);
        productService.delete(ids);
        return "redirect:findAll.do";//重定向到findAll方法
    }

}
