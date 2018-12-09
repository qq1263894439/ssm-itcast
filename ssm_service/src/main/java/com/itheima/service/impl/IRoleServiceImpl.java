package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IRoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IRoleServiceImpl  implements IRoleService{
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }

    }

    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return iRoleDao.findOtherPermission(roleId);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public void deleteByRoleId(String roleId) throws Exception {
        //从角色和权限关联表中删除角色
        iRoleDao.deleteUsers_RoleByRoleId(roleId);
        //从角色和用户关联表中删除角色
        iRoleDao.deleteRole_PermissionByRoleId(roleId);
        //从角色表删除
        iRoleDao.deleteByRoleId(roleId);

    }
}
