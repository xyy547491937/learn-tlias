package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    // 調用servcice
    // mappe
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    // 查詢部門信息
    // @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查詢部門全部日志");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    /*
     * 刪除
     * */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id 删除部门 {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(Dept dept) {
        log.info("新增部分的接口");
        return Result.success();
    }

    // 更新
    @Log
    @PutMapping
    public Result updata(@RequestParam Dept dept) {
        log.info("更新传入的数据{}",dept);
        deptService.updata(dept);
        return Result.success();
    }

}
