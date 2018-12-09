package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    List<SysLog> findAll(Integer page, Integer size) throws Exception;
    void save(SysLog log)throws Exception;
    public void deleteById(String id)throws Exception;

}
