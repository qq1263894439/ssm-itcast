package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

   // 根据用户id查询出所有对应的角色
   @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
   @Results({
           @Result(id = true, property = "id", column = "id"),
           @Result(property = "roleName", column = "roleName"),
           @Result(property = "roleDesc", column = "roleDesc"),
           @Result(property = "permissions",column = "id",many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))
   })
   public  List<Role> findRoleByUserId(String userId) throws Exception;

   //查询全部角色
   @Select("select * from role")
   public  List<Role> findAll() throws Exception;

   //添加角色
   @Select("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);


   //给角色添加权限
   @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
   public List<Permission> findOtherPermission(String roleId) throws Exception;


   @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
   public void addPermissionToRole(@Param("roleId") String  roleId, @Param("permissionId") String permissionId)throws Exception;

   @Select("select * from role where id=#{roleId}")
   @Results({
           @Result(id = true,property = "id",column = "id"),
           @Result(property = "roleName",column = "roleName"),
           @Result(property = "roleDesc",column = "roleDesc"),
           @Result(property = "permissions",column = "id",many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))
   })
   //查询角色详情
   Role findById(String roleId)throws Exception;

   //删除角色
   @Delete("delete from role where id=#{id}")
   public void deleteByRoleId(String roleId)throws Exception;

   @Delete("delete from users_role where roleId=#{roleId}")
   public void deleteUsers_RoleByRoleId(String roleId)throws Exception;


   @Delete("delete from role_permission where roleId=#{roleId}")
   public void deleteRole_PermissionByRoleId(String roleId)throws Exception;
}
