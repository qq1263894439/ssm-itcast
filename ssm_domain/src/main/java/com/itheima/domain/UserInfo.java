package com.itheima.domain;

import java.util.List;

//用户表
public class UserInfo {
    private String id;      //无意义，主键uuid
    private String username;    //用户名
    private String email;   //非空，唯一
    private String password; //密码（加密）
    private String phoneNum; //电话
    private int status;//状态0 未开启 1 开启
    private String statusStr; //控制状态
    private List<Role> roles; //角色描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        //状态0 未开启 1 开启
       if(status==0){
           statusStr="未开启";
       }else if(status==1){
           statusStr="开启";
       }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
