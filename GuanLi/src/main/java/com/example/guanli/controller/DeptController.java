package com.example.guanli.controller;

import com.example.guanli.anno.Log;
import com.example.guanli.pojo.Dept;
import com.example.guanli.pojo.Result;
import com.example.guanli.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
//1.查询所有
    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }
//2.根据id删除（简单参数）
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
//        {}占位符，{}中的内容会替换为方法参数的值，顺序一致
        log.info("根据id删除部门，id：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

//3.新增（json参数）
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门，dept：{}", dept);
        deptService.addDept(dept);
        return Result.success();
    }
//4.修改（json参数）
//      4.1.查询回显
//根据id查询（路径参数）
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        log.info("根据id查询部门，id：{}", id);
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }
//      4.2.修改数据
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门，dept：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
