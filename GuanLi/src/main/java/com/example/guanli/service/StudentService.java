package com.example.guanli.service;

import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Student;
import com.example.guanli.pojo.StudentQuery;

import java.util.List;

public interface StudentService {
    PageResult page(StudentQuery studentQuery);

    void deleteBatch(List<String> ids);

    void save(Student student);

    Student getById(String id);

    void update(Student student);

    void updateViolation(Integer id, Integer score);
}
