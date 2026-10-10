package com.example.guanli.controller;

import com.example.guanli.pojo.ClazzOption;
import com.example.guanli.pojo.JobOption;
import com.example.guanli.pojo.Result;
import com.example.guanli.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
@Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("获取员工职位数据");
        JobOption jobOption= reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("获取员工性别数据");
        List<Map<String, Object>> genderData = reportService.getEmpGenderData();
        return Result.success(genderData);
    }
    //学员学历统计
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("获取学员学历数据");
        List<Map<String,Object>> degreeData = reportService.getStudentDegreeData();
        return Result.success(degreeData);
    }
//统计班级人数
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("获取班级人数数据");
        ClazzOption studentCountData = reportService.getStudentCountData();
        return Result.success(studentCountData);
    }

}
