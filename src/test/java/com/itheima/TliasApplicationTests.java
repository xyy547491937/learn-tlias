package com.itheima;

import com.itheima.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class TliasApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt() {
        HashMap<String, Object> ss = new HashMap<>();
        ss.put("id", 2);
        ss.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima")
                .setClaims(ss)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testGenParseJwt() {
        Claims itheima = Jwts.parser().setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjIsImV4cCI6MTcwMTEzNzkzN30.6tg07bugH_CPfuCePgpaswIFVEi2w3obL5lQH1iOSaI")
                .getBody();

        System.out.println(itheima);
    }

    @Test
    public void decordToken() {
        String s = JwtUtil.decodeToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOCIsImV4cCI6MTcwMTM5Njk0Nn0.XPGe60H-2kD6VOAABa-5oDRxUWlXqNI3NAyM9Ay_ns78RdAx56uwVDkjZ5wlsY2LJlHg8WY66rKvvvY_to2T7Q");

        System.out.println(s);
    }

}
