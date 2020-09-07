package com.gfs.operatecenter.util;

import com.alibaba.fastjson.JSONObject;
import com.gfs.common.bean.TokenBean;
import com.gfs.common.constant.TokenConstant;
import com.gfs.common.enums.ResultEnum;
import com.gfs.common.util.StringUtil;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

import static com.gfs.common.constant.TokenConstant.TOKEN_KEY;

/**
 * Token工具类
 * @author liaodewen
 */
public class TokenUtil {

    public static String getToken(Map<String, Object> claims, Date expireDate) {
        String token = "";
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try {
            token = Jwts.builder()
                    .setSubject(TokenConstant.SUBJECT)
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                    .compact();
            token = TokenConstant.PRE_FIX + token;
        } catch (Exception e) {
            throw new GfsRuntimeException(ResultEnum.TOKEN_BUILD_ERROR);
        }
        return token;
    }

    public static Claims parseToken(String token) {
        if (StringUtil.isEmpty(token)) {
            throw new GfsRuntimeException(ResultEnum.TOKEN_EMPTY);
        }
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(TOKEN_KEY))
                    .parseClaimsJws(token.replaceFirst(TokenConstant.PRE_FIX, ""))
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GfsRuntimeException(ResultEnum.TOKEN_INVALID);
        } catch (Exception e) {
            throw new GfsRuntimeException(ResultEnum.TOKEN_ERROR);
        }
        return claims;
    }

    public static TokenBean getTokenBean(String token) {
        Claims claims = parseToken(token);
        JSONObject tokenObj = JSONObject.parseObject(JSONObject.toJSONString(claims.get(TokenConstant.TOKEN_BEAN_NAME)));
        TokenBean bean = JSONObject.toJavaObject(tokenObj, TokenBean.class);
        return bean;
    }

}
