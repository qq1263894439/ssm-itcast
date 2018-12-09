package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaTypeEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

//产品Controller
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

   /* @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new MyDateEdit("yyyy-MM-dd HH:mm");
    }*/
   //产品添加
   @RequestMapping("/save.do")
   public String save(Product product) throws Exception{
        productService.save(product);
        //重定向到findAll.do
        return"redirect:findAll.do";
   }

   //产品查询
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page,size);
        PageInfo pageInfo =new PageInfo(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/deleteProduct.do")
    public String deleteProduct(String id) throws Exception {
       productService.deleteProduct(id);
       return "redirect:findAll.do";
    }
}