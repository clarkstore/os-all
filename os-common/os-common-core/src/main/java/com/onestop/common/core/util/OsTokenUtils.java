/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.onestop.common.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

/**
 * Token工具类
 * TODO hutool-jwt改造
 *
 * @author Clark
 * @version 2021-01-07
 */
public class OsTokenUtils {
    /**
     * 设置token的Secret
     */
    private String secret;
    /**
     * 设置token过期时间
     */
    private int expireTimeInMinutes;
    /**
     * 设置token的claim
     */
    private String claimKey;

    public OsTokenUtils(String secret, int expireTimeInMinutes, String claimKey) {
        this.secret = secret;
        this.expireTimeInMinutes = expireTimeInMinutes;
        this.claimKey = claimKey;
    }

    /**
     * 生成签名
     * @return 签名
     */
    public String sign() {
        return this.sign(null);
    }

    /**
     * 生成签名
     * @param claimValue 通过声明关键字生成，并通过关键字验签
     * @return 签名
     */
    public String sign(String claimValue) {
        try {
            // 设置过期时间
            Date date = DateUtil.offsetMinute(DateUtil.date(), this.expireTimeInMinutes);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            // 设置头部信息
            Map<String, Object> header = MapUtil.newHashMap();
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim(this.claimKey, claimValue)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param token Token
     * @return boolean
     */
    public boolean verify(String token) {
        return this.verify(token, null);
    }

    /**
     * 检验token是否正确：claimValue匹配
     *
     * @param token Token
     * @param claimValue 通过声明关键字生成，并通过关键字验签
     * @return boolean
     */
    public boolean verify(String token, String claimValue) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
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
     * @param token Token
     * @return
     */
    private String getClaimFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(this.claimKey).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
