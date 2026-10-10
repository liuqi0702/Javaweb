package com.example.guanli.service.impl;

import com.example.guanli.mapper.EmpExprMapper;
import com.example.guanli.mapper.EmpMapper;
import com.example.guanli.pojo.*;
import com.example.guanli.service.EmpLogService;
import com.example.guanli.service.EmpService;
import com.example.guanli.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpI implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional//事务管理
    @Override
    public void save(Emp emp) {
        try {
//        1.新增员工信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
//        2.新增员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr ->
                        empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //           3.记录员工操作日志
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //        1.删除员工信息
        empMapper.deleteByIds(ids);

//        2.删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);

    }

    @Override
    public Emp getById(Integer id) {
        return  empMapper.getById(id);


    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.修改员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.修改员工工作经历
        //2.1 先删除员工工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 后新增员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr ->
                    empExpr.setEmpId(emp.getId()));
        empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
         Emp e= empMapper.login(emp);
         if (e!=null){
             //生成Jwt令牌
             Map<String, Object> claims = new HashMap<>();
             claims.put("id", e.getId());
             claims.put("username", e.getUsername());
             claims.put("password", e.getPassword());
             String token = JwtUtils.generateJwt(claims);
             return new LoginInfo(e.getId(),e.getUsername(),e.getPassword(),token);
         }
         return null;
    }


//    原始分页查询
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        long total = empMapper.count();
//        Integer start=(page-1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<>(total, rows);
//    }
//
//@Override
//public PageResult<Emp> page(Integer page, Integer pageSize, String name, String gender, LocalDate begin, LocalDate end) {
////    1.设置分页参数
//    PageHelper.startPage(page, pageSize);
////    2.查询数据
//    List<Emp> rows = empMapper.list(name, gender, begin, end);
////    3.获取分页结果
//    Page<Emp> p = (Page<Emp>) rows;
//    return new PageResult<Emp>(p.getTotal(), p.getResult());
//}

}
