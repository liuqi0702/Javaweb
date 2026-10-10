package com.example.guanli.controller;

import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Result;
import com.example.guanli.pojo.Student;
import com.example.guanli.pojo.StudentQuery;
import com.example.guanli.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")

public class StudentController {
    @Autowired
    private StudentService studentService;
//列表查询
    @GetMapping
    public Result Page(StudentQuery studentQuery){
        log.info("分页查询学生，{}", studentQuery.toString());
        PageResult<Student> pageResult = studentService.page(studentQuery);
        return Result.success(pageResult);
    }
    //批量删除
    @DeleteMapping("/{ids}")
    public Result deleteBatch(@PathVariable List<String> ids){
        log.info("批量删除学生，{}", ids);
        studentService.deleteBatch(ids);
        return Result.success();
    }
    //新增学生
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("新增学生，{}", student.toString());
        studentService.save(student);
        return Result.success();
    }
    //修改学生
    //根据id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable String id){
        log.info("根据id查询学生，{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    //修改学生
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生，{}", student.toString());
        studentService.update(student);
        return Result.success();
    }
    //修改违纪
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score){
        studentService.updateViolation(id, score);
        return Result.success();
    }

}
