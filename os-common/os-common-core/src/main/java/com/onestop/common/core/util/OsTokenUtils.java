package com.onestop.common.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.onestop.common.core.config.TokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * Token工具类
 *
 * @author Clark
 * @version 2021-01-07
 */
@Configuration
@EnableConfigurationProperties(TokenProperties.class)
public class OsTokenUtils {
    @Autowired
    private TokenProperties properties;

    /**
     * 生成签名
     *
     * @return 签名
     */
    public String sign() {
        return sign(null, null);
    }

    /**
     * 生成签名
     *
     * @param claimValue 通过声明关键字生成，并通过关键字验签
     * @return 签名
     */
    public String sign(String claimValue) {
        return sign(claimValue, null);
    }

    /**
     * 生成签名
     *
     * @param expireTime
     * @return 签名
     */
    public String sign(long expireTime) {
        return sign(null, expireTime);
    }

    /**
     * 生成签名
     *
     * @param claimValue 通过声明关键字生成，并通过关键字验签
     * @param expireTime 自定义过期时长
     * @return 签名
     */
    public String sign(String claimValue, Long expireTime) {
        try {
            // 设置默认值
            if (StrUtil.isBlank(claimValue)) {
                claimValue = this.properties.getClaim();
            }
            // 设置2小时过期
            if (expireTime == null) {
                expireTime = this.properties.getExpireTime();
            }
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + expireTime);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(this.properties.getSecret());
            // 设置头部信息
            Map<String, Object> header = CollUtil.newHashMap();
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim(this.properties.getClaim(), claimValue)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确：keyword匹配
     *
     * @param token
     * @return boolean
     */
    public boolean verify(String token) {
        return verify(token, null);
    }

    /**
     * 检验token是否正确：claimValue匹配
     *
     * @param token
     * @param claimValue 通过声明关键字生成，并通过关键字验签
     * @return boolean
     */
    public boolean verify(String token, String claimValue) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.properties.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            if (StrUtil.isNotBlank(claimValue)) {
                String tokenClaimValue = getClaimFromToken(token);
                if (!claimValue.equals(tokenClaimValue)) {
                    return false;
                }
            }

            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从token中获取Claim信息
     *
     * @param token
     * @return
     */
    private String getClaimFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(this.properties.getClaim()).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
