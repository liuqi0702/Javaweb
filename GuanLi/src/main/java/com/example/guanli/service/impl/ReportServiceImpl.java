package com.example.guanli.service.impl;

import com.example.guanli.mapper.EmpMapper;
import com.example.guanli.mapper.StudentMapper;
import com.example.guanli.pojo.ClazzOption;
import com.example.guanli.pojo.JobOption;
import com.example.guanli.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
       //1.调用Mapping获取数据
        List<Map<String, Object>> data = empMapper.countEmpJobData();
        //2.组装数据并返回
        List<Object> jobList = data.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> datalist = data.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList, datalist);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> data = studentMapper.countStudentCountData();
        List<Object> clazzList = data.stream().map(dataMap->dataMap.get("clazz")).toList();
        List<Object> dataList = data.stream().map(dataMap->dataMap.get("num")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
