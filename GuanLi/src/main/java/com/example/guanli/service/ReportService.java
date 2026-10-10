package com.example.guanli.service;

import com.example.guanli.pojo.ClazzOption;
import com.example.guanli.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
