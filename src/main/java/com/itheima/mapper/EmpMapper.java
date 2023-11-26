package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page (Integer start, Integer pageSize) ;

    /*
    员工信息的查询
    * */
    // @Select("select * from  emp")
    public List<Emp> list(
            String name,
            Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    );

    public void delete(List<Integer> ids);

    @Insert("insert into  emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)  " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime}) ")
    void insert(Emp emp);

    @Select("select * from emp where id =#{id}")
    Emp getById(Integer id);
    void update(Emp emp);
}
