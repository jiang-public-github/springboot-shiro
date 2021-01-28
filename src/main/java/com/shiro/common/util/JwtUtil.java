package com.shiro.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author jianglanglang
 * @Date 2021/1/26 13:25
 * @Description JWT生成token
 */
public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 生成token秘钥
     */
    public static final String TOKEN_SECRET_KEY = "token&a7972ba2d89c450ba238f3fc4282fde0";

    /**
     * token过期时间/分钟 默认8个小时
     */
    public static final int FAILURE_TIME = 8*60;


    /**
     * 生成 JWT token 字符串
     * @param userName 登录名
     * @param expirationTime 过期时间/分钟
     * @param claims 额外添加到荷部分的信息（用户编号、用户名、密码等；如果是移动端可携带设备信息）
     * @return
     * @throws Exception
     */
    public static String createToken(String userName, Integer expirationTime, Map<String,Object> claims) {
        claims = claims != null ? claims : new HashMap<>(16);
        // token签发时间，token过期时间
        Date issueDate = issueDate();
        Date expirationDate  = expirationDate(expirationTime);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET_KEY);
        // 设置头部信息
        Map<String , Object> map = new HashMap<String , Object>(16);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        // 生成token字符串 JWT 由 Header:头部 Payload:负载 Signature:签名 三部分组成
        String token = JWT.create()
                .withHeader(map)
                .withClaim("claims", claims)
                .withClaim("timestamp", System.currentTimeMillis())
                .withSubject(userName)
                .withIssuedAt(issueDate)
                .withExpiresAt(expirationDate)
                .withJWTId(UUID.randomUUID().toString().replace("-", ""))
                .sign(algorithm);

        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.info("----------------token凭证错误或失效!----------------");
            return false;
        }
    }
    /**
     * 解析token
     * @param token
     * @return
     */
    public Map<String,Object> analysisToken(String token) {
        DecodedJWT jwt = null;
        Map<String,Object> resultMap = new HashMap<>(6);
        try {
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
            Map<String,Claim> claimMap = jwt.getClaims();
            resultMap.put("subject", claimMap.get("sub").asString());
            resultMap.put("claims", claimMap.get("claims").asMap());
            resultMap.put("expiresAt", claimMap.get("exp").asDate());
            resultMap.put("issuedAt", claimMap.get("iat").asDate());
            resultMap.put("jwtId", claimMap.get("jti").asString());
            resultMap.put("timestamp", claimMap.get("timestamp").asDate());
        } catch (Exception e) {
            logger.info("----------------token凭证错误或失效!----------------");
            throw new RuntimeException("token凭证错误或失效！");
        }
        return resultMap;
    }

    /**
     * token签发时间
     * @return
     */
    private static Date issueDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return changingAllTime(localDateTime);
    }

    /**
     * token过期时间
     * @param expirationDate 过期时间/分钟
     * @return
     */
    private static Date expirationDate(Integer expirationDate){
        LocalDateTime localDateTime = LocalDateTime.now();
        return changingAllTime(localDateTime.plusMinutes(expirationDate == null ? FAILURE_TIME : expirationDate));
    }

    /**
     * LocalDateTime 转 Date
     * @param localDateTime
     * @return
     */
    private static Date changingAllTime(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }


    public static void main(String[] args) throws InterruptedException {
        String token = null;
        Map<String,Object> claims = new HashMap<>(16);
        claims.put("userId", "超级管理员");
        claims.put("phone", "15285326224");
        claims.put("userId", "5ed01be45e434bb0a3b866cd88da37f7");
        token = createToken("admin", 1, claims);
        System.out.println("token："+token);

        JwtUtil jwtUtil = new JwtUtil();
        System.out.println("token验证："+ jwtUtil.verifyToken(token));
        // 获取token中的参数
        Map<String,Object> map = jwtUtil.analysisToken(token);
        map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));


        // 过期验证
        System.out.println("\n");
        Thread.sleep(1000*50);
        System.out.println("token："+ jwtUtil.verifyToken(token));

        System.out.println("\n");
        Thread.sleep(1000*61);
        System.out.println("token："+ jwtUtil.verifyToken(token));
    }
}