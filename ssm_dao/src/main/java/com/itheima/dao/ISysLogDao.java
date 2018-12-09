package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ISysLogDao {

    @Select("select * from syslog")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "visitTime",property = "visitTime"),
            @Result(column = "username",property = "username"),
            @Result(column = "ip",property = "ip"),
            @Result(column = "url",property = "url"),
            @Result(column = "executionTime",property = "executionTime"),
            @Result(column = "method",property = "method"),
    })
    public List<SysLog> findAll() throws Exception;


    @Insert("insert into syslog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog log) throws Exception;

    @Delete("delete from syslog where id=#{id}")
    public void deleteById(String id)throws Exception;

    @Delete("delete from syslog")
    public void deleteAll() throws Exception;
}
