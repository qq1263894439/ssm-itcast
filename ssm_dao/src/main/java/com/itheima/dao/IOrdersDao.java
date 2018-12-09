package com.itheima.dao;

import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

//订单Dao
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one=@One(select = "com.itheima.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;


    @Select("select * from orders where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one=@One(select = "com.itheima.dao.IProductDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.itheima.dao.ITravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.itheima.dao.IMemberDao.findById"))
    })
    Orders findById(String id) throws Exception;

    @Delete("delete from orders where id=#{id}")
    public void deleteOrders(String id);

    @Delete("delete from order_traveller where orderId=#{orderId}")
    public void deleteOrder_traveller(String id);

    @Insert("insert into orders (ordernum,ordertime,peoplecount,orderdesc,paytype,orderstatus,productid,memberid) values(#{ordernum},#{ordertime},#{peoplecount},#{orderdesc},#{peoplecount},#{orderdesc},#{paytype},#{orderstatus},#{productid},#{memberid})")
    void save (Orders orders)throws Exception;
}
