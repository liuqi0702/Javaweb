package com.example.guanli.mapper;

import com.example.guanli.pojo.Student;
import com.example.guanli.pojo.StudentQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> page(StudentQuery studentQuery);

    void deleteBatch(List<String> ids);

    void save(Student student);
    @Select("select * from student where id = #{id}")
    Student getById(String id);

    void update(Student student);
    @Update("update student set violation_score=#{score} where id=#{id}")
    void updateViolation(Integer id, Integer score);

    List<Map<String, Object>> countStudentDegreeData();

    List<Map<String, Object>> countStudentCountData();
}
