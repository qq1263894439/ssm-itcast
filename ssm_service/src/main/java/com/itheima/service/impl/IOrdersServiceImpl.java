package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//订单Service
@Service
public class IOrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao dao;
    @Override
    public List<Orders> findAll(Integer page, Integer size) throws Exception {
        //参数pageNum是页码值,pageSize是每页显示条数
        PageHelper.startPage(page,size);
        return dao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public void deleteOrders(String id) {
        //删除Order_traveller中orderId
        dao.deleteOrder_traveller(id);
        //删除OrdersId
        dao.deleteOrders(id);
    }

    @Override
    public void save(Orders orders) throws Exception {
        dao.save(orders);
    }
}
