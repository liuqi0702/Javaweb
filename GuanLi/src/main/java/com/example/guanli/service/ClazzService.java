package com.example.guanli.service;

import com.example.guanli.pojo.Clazz;
import com.example.guanli.pojo.ClazzQuery;
import com.example.guanli.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQuery clazzQuery);

    void deleteById(Integer id);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();
}
