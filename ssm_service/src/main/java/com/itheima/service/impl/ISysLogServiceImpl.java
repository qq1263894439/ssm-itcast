package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ISysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog log) throws Exception {
        sysLogDao.save(log);
    }

    @Override
    public void deleteById(String id) throws Exception {
        sysLogDao.deleteById(id);
    }

}
