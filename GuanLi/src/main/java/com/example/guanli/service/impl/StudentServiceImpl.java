package com.example.guanli.service.impl;

import com.example.guanli.mapper.StudentMapper;
import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Student;
import com.example.guanli.pojo.StudentQuery;
import com.example.guanli.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> page(StudentQuery studentQuery) {

        PageHelper.startPage(studentQuery.getPage(), studentQuery.getPageSize());
        List<Student> students = studentMapper.page(studentQuery);
        Page<Student> page = (Page<Student>) students;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteBatch(List<String> ids) {
        studentMapper.deleteBatch(ids);
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }

    @Override
    public Student getById(String id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }
}
