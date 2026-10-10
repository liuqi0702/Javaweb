package com.example.guanli.service.impl;

import com.example.guanli.exception.BusinessException;
import com.example.guanli.mapper.DeptMapper;
import com.example.guanli.mapper.EmpMapper;
import com.example.guanli.pojo.Dept;
import com.example.guanli.service.DeptService;
import com.example.guanli.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        // 判断部门下是否有员工
        int count = empMapper.countByDeptId(id);
        if (count > 0) {
            throw new BusinessException("该部门下有员工，不能删除");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
