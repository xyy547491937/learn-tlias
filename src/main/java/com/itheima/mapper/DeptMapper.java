package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where  id = #{id}")
    void deleteById(Integer id);

   // @Insert("insert  into  dept( name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

//    String accessKey = "c85fQObE2fr9hP0jVerIMXFqJ36mh6QM7tntEeM_";
//    String secretKey = "_CLSqhPo-w9Leoo-O-urkyGjZCK4WjMG6DCZrkOI";
    // 更新
    void updata(Dept dept);
}
