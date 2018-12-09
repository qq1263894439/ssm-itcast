package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    //查询与role关联的所有的权限
    //findPermissionByRoleId
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll()throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission p)throws Exception;

    @Select("select * from permission where id=#{id}")
    public Permission findById(String permissionId) throws Exception;

    @Select("delete from role_permission where permissionId=#{id}")
    public void deleteRole_PermissionById(String permissionId)throws Exception;

    @Delete("delete from permission where id=#{id}")
    public void deleteById(String id)throws Exception;


}
