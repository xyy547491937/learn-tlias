package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    /*
    * cookie 请求登录 服务端 把cookie 写入客户端
    * */
    @GetMapping("c1")
    public Result c1(HttpServletResponse response) {
        response.addCookie(new Cookie("itheima","xieyayu"));

        return Result.success();
    }
    /*
    * JWT（JSON Web Token）是一种用于在网络上安全传递声明的开放标准。JWT 可以被签名（使用 HMAC 算法或 RSA 的公钥/私钥对）以保证信息的完整性，并且可以加密以提供信息的保密性。JWT 的常见用途包括身份验证和信息交换。
    * */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录参数为{}", emp);
        Emp e = empService.login(emp);
        return e != null ? Result.success() : Result.error("用户民或者密码错误");
    }
}
