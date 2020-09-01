//package com.dw.sc.gateway.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dw.sc.gateway.constant.TokenConstant;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.xml.bind.DatatypeConverter;
//import java.util.Date;
//import java.util.Map;
//
///**
// * Token工具类
// * @author liaodewen
// */
//public class TokenUtil {
//
//    public static String getToken(Map<String, Object> claims, Date expireDate) {
//        String token = "";
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        try {
//            token = Jwts.builder()
//                    .setSubject(TokenConstant.SUBJECT)
//                    .setClaims(claims)
//                    .setIssuedAt(now)
//                    .setExpiration(expireDate)
//                    .signWith(SignatureAlgorithm.HS512, TokenConstant.TOKEN_KEY)
//                    .compact();
//            token = TokenConstant.PRE_FIX + token;
//        } catch (Exception e) {
//            throw new GfsRuntimeException(ResultEnum.TOKEN_BUILD_ERROR);
//        }
//        return token;
//    }
//
//    public static Claims parseToken(String token) {
//        if (StringUtils.isEmpty(token)) {
////            throw new GfsRuntimeException(ResultEnum.TOKEN_EMPTY);
//            throw new GfsRuntimeException(400,"abc");
//        }
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(DatatypeConverter.parseBase64Binary(TokenConstant.TOKEN_KEY))
//                    .parseClaimsJws(token.replaceFirst(TokenConstant.PRE_FIX, ""))
//                    .getBody();
//        } catch (ExpiredJwtException e) {
//            throw new GfsRuntimeException(ResultEnum.TOKEN_INVALID);
//        } catch (Exception e) {
//            throw new GfsRuntimeException(ResultEnum.TOKEN_ERROR);
//        }
//        return claims;
//    }
//
//    public static TokenBean getTokenBean(String token) {
//        Claims claims = parseToken(token);
//        JSONObject tokenObj = JSONObject.parseObject(JSONObject.toJSONString(claims.get(TokenConstant.TOKEN_BEAN_NAME)));
//        TokenBean bean = JSONObject.toJavaObject(tokenObj, TokenBean.class);
//        return bean;
//    }
//
//}
