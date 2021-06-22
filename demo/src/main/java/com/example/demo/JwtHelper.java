package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.example.demo.dto.UserDto;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {

    private static String TOKEN_PREFIX = "Bearer";
    private static String TOKEN_SECRET = "jhkasjkhfksdkskkskkswdffdagb";

    /*创建token，依据用户模型*/
    public static String createToken(UserDto userDto) {
        Map<String, String> claims = new HashMap<>();
        claims.put("userId", String.valueOf(userDto.getUserId()));
        claims.put("userName", userDto.getUserName());

        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE, 7);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        claims.forEach(builder::withClaim);
        String token = builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        return token;

    }

    /*验证token，并且返回用户token的模型*/
    public static UserDto verifyToken(String token) {
        token = token.replaceFirst(TOKEN_PREFIX, "").trim();
        DecodedJWT verification=null;
        try{
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
        }
        catch (Exception e){
        }
        if (verification == null) {
            return null;
        }
        UserDto user = new UserDto();
        user.setUserId(Integer.parseInt(verification.getClaim("userId").asString()));
        user.setUserName(verification.getClaim("userName").asString());
        if (user.getUserId() <= 0) {
            return null;
        }
        return user;
    }

}
