package com.scuec.restaurant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Zc
 * @create: 2022-10-25
 */
public class JwtUtils {

    /**
     签发对象：这个用户的id
     签发时间：现在
     有效时间：30分钟
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：个人id加上一串字符串
     */
    public static String createToken(String userId, String Password) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,30);  //有效时间
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withAudience(userId, Password)             //签发对象
                .withIssuedAt(new Date())                   //发行时间
                .withExpiresAt(expiresDate)                 //有效时间
                .withClaim("userId", userId)          //载荷
                .sign(Algorithm.HMAC256(userId+Password));   //个人ID+密码一起加密
    }

    /**
     * 检验合法性，其中secret参数是用户的id+password
     * @param token
     * @throws GlobalException 校验失败异常
     */
    public static void verifyToken(String token, String secret) throws GlobalException {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            throw new GlobalException(ResponseCode.ERROR, "token校验失败");
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws GlobalException {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new GlobalException(ResponseCode.ERROR, "token解析失败");
        }
        return audience;
    }
    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String userId){
        return JWT.decode(token).getClaim(userId);
    }
}
