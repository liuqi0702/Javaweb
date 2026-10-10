package com.example.guanli.service.impl;

import com.example.guanli.mapper.ClazzMapper;
import com.example.guanli.pojo.Clazz;
import com.example.guanli.pojo.ClazzQuery;
import com.example.guanli.pojo.PageResult;
import com.example.guanli.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    //分页条件查询
    @Override
    public PageResult<Clazz> page(ClazzQuery clazzQuery) {
        PageHelper.startPage(clazzQuery.getPage(),clazzQuery.getPageSize());
        List<Clazz> clazzList = clazzMapper.page(clazzQuery);
        //设置状态，根据结束时间判断
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate().isBefore(LocalDate.now())) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
        Page<Clazz> page= (Page<Clazz>) clazzList;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.save(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return  clazzMapper.getById(id);

    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
