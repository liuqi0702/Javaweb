package com.example.guanli.mapper;

import com.example.guanli.pojo.Clazz;
import com.example.guanli.pojo.ClazzQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> page(ClazzQuery clazzQuery);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void save(Clazz clazz);
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    void update(Clazz clazz);
    @Select("select * from clazz")
    List<Clazz> findAll();
}
