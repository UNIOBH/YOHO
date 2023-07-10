package com.uniobh.yoho.utils;

import com.uniobh.yoho.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    /**
     * 生成token
     * @param user 用户信息
     * @return token字符串
     */
    public static String generateToken(User user) {
        return Jwts.builder()
                // header
                .setHeaderParam("typ", "JWT")    // token类型
                .setHeaderParam("alg", "HS256")  // 算法名称
                // payload
                .claim("username", user.getName())
                .claim("password", user.getPassword())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))    // 设置token有效时间 7天
                // signature
                .signWith(SignatureAlgorithm.HS256, user.getName()) // 根据用户名进行签名
                .compact();
    }

    /**
     * 检查token
     * @param username 用户名
     * @param token 需要判断的token
     * @return
     */
    public static boolean checkToken(String username, String token) {
        if(token == null || "".equals(token)) return false;
        try {
            // 解析token成功表示token有效，当解析过程中报异常表示token失效
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(username).parseClaimsJws(token);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
