package com.example.guanli.mapper;

import com.example.guanli.pojo.Emp;
import com.example.guanli.pojo.EmpExpr;
import com.example.guanli.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取插入数据的自增主键--主键返回
    @Insert("insert into emp( username,  name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "VALUES(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();
    @Select("select * from emp")
    List<Emp> findAll();
    @Select("select count(*) from emp where dept_id=#{deptId}")
    Integer countByDeptId(Integer deptId);
    @Select("select id,username,password from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);

//    原始分页查询
//    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
//    public long count();
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc limit #{start},#{pageSize} ")
//    public List<Emp> list(Integer start,Integer pageSize);
//    分页插件
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
//            "                           where e.name like '%#{name}%' and e.gender='#{gender}' and e.entry_date between '#{begin}' and '#{end}'" +
//            "                           order by e.update_time desc")
    //public List<Emp> list(String name, String gender, LocalDate begin, LocalDate end);


}
