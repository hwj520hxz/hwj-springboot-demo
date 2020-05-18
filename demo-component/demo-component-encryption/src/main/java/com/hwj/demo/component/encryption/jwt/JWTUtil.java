package com.hwj.demo.component.encryption.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：JWT工具类
 */
public class JWTUtil {

    public static final String TOKEN = "Authorization";
    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USER_NAME = "userName";

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, Long userId, String secret){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);  //HMAC256签名加密算法实例
            // 通过调用JWT.require和算法实例创建JWT验证器
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withClaim(CLAIM_USER_ID, userId)  // 规则：是否包含userId
                                      .build();
            DecodedJWT jwt = verifier.verify(token);  // 验证签名
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户id
     */
    public static Long getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token); // 将签名进行解码
            return jwt.getClaim(CLAIM_USER_ID).asLong(); //数据是以键值对的形式存在，
        } catch (JWTDecodeException e){
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户账号
     */
    public static String getUserName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token); // 将签名进行解码
            return jwt.getClaim(CLAIM_USER_NAME).asString(); //数据是以键值对的形式存在，
        } catch (JWTDecodeException e){
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param userId 用户Id
     * @param secret 用户密码
     * @return 加密的token
     * withHeader 设置头部信息
     * withIssuer 设置签名是谁生成的
     * withSubject 设置签名主题
     * withAudience 设置谁接受签名
     * withIssuedAt 设置生成签名的时间
     */
    public static String sign(Long userId, String userName, String secret){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withClaim(CLAIM_USER_ID, userId)
                    .withClaim(CLAIM_USER_NAME, userName) //设置签名中需要携带的信息
                    .withExpiresAt(getExpiresTime())  //设置生成签名过期时间
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e){
            return null;
        }
    }

    /**
     * 获取过期时间
     *
     * @return
     */
    private static Date getExpiresTime() {
        LocalDate date = LocalDate.now().plusDays(1);
        LocalDateTime time = LocalDateTime.of(date, LocalTime.parse("04:30:00"));
        return Date.from(time.atZone(ZoneId.of("+8")).toInstant());
    }
}
