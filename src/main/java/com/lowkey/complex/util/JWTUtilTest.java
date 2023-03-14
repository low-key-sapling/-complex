package com.lowkey.complex.util;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuanjifan
 * @description
 * @date 2022年05月20日 15:43
 */
public class JWTUtilTest {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 70 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "c369a1e4-43ee-4e1e-b130-2b952f1ba9ad";

    public static void main(String[] args) {

        HashMap<String, String> payload = Maps.newHashMap();
        payload.put("nonce", UUID.randomUUID().toString());
        String hmac256Token = createHMAC256Token(payload, TOKEN_SECRET);
        System.out.println(hmac256Token);
        String s = validateHMAC256Token(hmac256Token);
        System.out.println(s);
        String s1 = parseHMAC256Token(hmac256Token);
        System.out.println(s1);
    }

    public static String createHMAC256Token(Map<String, String> payload, String tokenSecret) {
        // 秘钥和加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        JWTCreator.Builder builder = JWT.create();
        payload.forEach(builder::withClaim);
        builder.withExpiresAt(date);
        builder.withPayload(payload);
        return builder.sign(algorithm);
    }

    public static String parseHMAC256Token(String token) {
        DecodedJWT decode = JWT.decode(token);
        System.out.println(decode.getClaim("nonce"));
        String payload = decode.getPayload();
        System.out.println("payload"+payload);
        Claim a = decode.getClaim("a");
        return decode.toString();
    }

    public static String validateHMAC256Token(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT verify = verifier.verify(token);
            return "true";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "false";
        }
    }
}
