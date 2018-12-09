package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.dao.IOrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

//订单Controller
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService service;

    //分页查询全部订单

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "5") Integer size)throws Exception{
        List<Orders> ordersList = service.findAll(page,size);
        //PageInfo是一个分页类
        PageInfo pageInfo =new PageInfo(ordersList);
        ModelAndView mv =new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
    //订单详情
    @RequestMapping("/findById.do")
    @Secured("ROLE_USER")
    public ModelAndView findById(String id) throws Exception {
        Orders orders = service.findById(id);
        ModelAndView mv =new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
    @RequestMapping("/deleteOrders.do")
    public String deleteOrders(@RequestParam(name="id",required = true)String id){
        service.deleteOrders(id);
        return "redirect:findAll.do";
    }
    @RequestMapping("/save.do")
    public String save(Orders orders)throws Exception{
        service.save(orders);
        return "redirect:findAll.do";
    }
}
