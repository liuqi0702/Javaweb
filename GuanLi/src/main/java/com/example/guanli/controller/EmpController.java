package com.example.guanli.controller;

import com.example.guanli.pojo.Emp;
import com.example.guanli.pojo.EmpQueryParam;
import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Result;
import com.example.guanli.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

//    分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询员工，{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询所有员工");
        List<Emp> emps = empService.findAll();
        return Result.success(emps);
    }

//    新增员工与员工经历
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("添加员工，{}", emp);
        empService.save(emp);
        return Result.success();
    }
//    删除员工--数组形式删除
//    @DeleteMapping
//    public Result delete(@RequestBody Integer[] ids){
//        log.info("批量删除员工，{}", ids);
//        empService.deleteBatch(ids);
//        return Result.success();
//    }

    //    删除员工--List形式删除
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工，{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工，{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工，{}", emp);
        empService.update(emp);
        return Result.success();
    }



}
