package com.example.guanli.service;

import com.example.guanli.pojo.PageResult;

public interface LogService {
    PageResult page(Integer page, Integer pageSize);
}
