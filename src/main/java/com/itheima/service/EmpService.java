package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean page(Integer page, Integer pageSize,
                  String name,
                  Short gender,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    );

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp getById(Integer id);

    // 员工更新
    void update(Emp emp);

    // 员工登录
    Emp login(Emp emp);
}
