package com.itheima;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
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
        ss.put("id",2);
        ss.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima")
                .setClaims(ss)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

}
