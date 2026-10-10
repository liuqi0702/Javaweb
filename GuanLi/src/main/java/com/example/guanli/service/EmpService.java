package com.example.guanli.service;

import com.example.guanli.pojo.Emp;
import com.example.guanli.pojo.EmpQueryParam;
import com.example.guanli.pojo.LoginInfo;
import com.example.guanli.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    List<Emp> findAll();

    LoginInfo login(Emp emp);


    //PageResult<Emp> page(Integer page, Integer pageSize, String name, String gender, LocalDate begin, LocalDate end);

}
