package com.example.guanli.controller;

import com.example.guanli.pojo.Clazz;
import com.example.guanli.pojo.ClazzQuery;
import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Result;
import com.example.guanli.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;


    @GetMapping
    //条件分页查询
    public Result page(ClazzQuery clazzQuery) {
        log.info("分页查询班级，{}", clazzQuery.toString());
        PageResult<Clazz> pageResult = clazzService.page(clazzQuery);
        return Result.success(pageResult);
    }
//删除班级
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除班级，id：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
//添加班级
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级，{}", clazz.toString());
        clazzService.save(clazz);
        return Result.success();
    }
//修改班级
    //数据回显（根据id查询班级）
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询班级，id：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    //修改数据
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级，{}", clazz.toString());
        clazzService.update(clazz);
        return Result.success();
    }

    //查询所有班级
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

}
