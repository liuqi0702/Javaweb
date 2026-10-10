package com.example.guanli.service;

import com.example.guanli.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void  deleteById(Integer id);

    void addDept(Dept dept);

    Dept getDeptById(Integer id);

    void update(Dept dept);
}
