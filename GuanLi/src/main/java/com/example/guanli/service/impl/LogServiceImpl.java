package com.example.guanli.service.impl;

import com.example.guanli.mapper.EmpMapper;
import com.example.guanli.mapper.OperateLogMapper;
import com.example.guanli.pojo.Emp;
import com.example.guanli.pojo.OperateLog;
import com.example.guanli.pojo.PageResult;
import com.example.guanli.service.EmpService;
import com.example.guanli.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = operateLogMapper.page(page, pageSize);
        for (OperateLog log : list) {
            Emp emp = empMapper.getById(log.getOperateEmpId());
            log.setOperateEmpName(emp.getName());
        }
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<>(p.getTotal(), p.getResult());

    }
}
