package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    // 查询
    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {

        log.info("传入的pag啊和pageSzie为{}，{}", page, pageSize);
        log.info(name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工操作{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    // 新增员工

    @Log
    @PostMapping
    public  Result save(@RequestBody  Emp emp) {
        log.info("新增员工的emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /*
    * 根据员工id 查询员工信息
    * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据员工查询员工信息{}",id);
        Emp emp = empService.getById(id);
        return  Result.success(emp);
    }

    /*
    * 修改員工
    * */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新員工的信息{}",emp);
        empService.update(emp);
        return Result.success();
    }


}
