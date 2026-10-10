package com.example.guanli.controller;

import com.example.guanli.pojo.PageResult;
import com.example.guanli.pojo.Result;
import com.example.guanli.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
@Autowired
private LogService logService;


    @GetMapping("/page")
    public Result logPage(@RequestParam(defaultValue = "1") Integer page, //默认值1
                          @RequestParam(defaultValue = "10") Integer pageSize) { //默认值10
        log.info("分页查询日志，页码：{}，页大小：{}", page, pageSize);
        PageResult pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }

}
