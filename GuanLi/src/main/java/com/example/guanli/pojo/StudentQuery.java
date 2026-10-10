package com.example.guanli.pojo;

import lombok.Data;

@Data
public class StudentQuery {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page;
    private Integer pageSize;
}
