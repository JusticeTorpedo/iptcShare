package cn.edu.ncut.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.Cookie;
import java.util.Base64;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    //密钥
    private static final String SIGN = "#$423DFsd23&*(4!@#da";

    /**
     * 按照默认存活时间生成Token
     * @param map,表示浏览器传来的数据,作为payload加载到token中;
     * @return 生成的token
     */
    public static String createToken(Map<String,String> map){
        //30分钟过期
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //使用循环遍历map，加载payload
        map.forEach((key,value)->{
            builder.withClaim(key,value);
        });

        //指定过期时间和sign，创建token
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    /**
     * 指定存活时间，生成token
     * @param map,表示浏览器传来的数据,作为payload加载到token中;
     * @param instance,表示生成的token的存活时间;
     * @return 生成的token
     */
    public static String createTokenWithTime(Map<String, String> map, Calendar instance){

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //使用循环遍历map，加载payload
        map.forEach((key,value)->{
            builder.withClaim(key,value);
        });

        //指定过期时间和sign，创建token
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    /**
     * 验证Token
     *  如果Token非法，则会抛出异常;
     *  如果Token正常，则会返回解码后的Token;
     * @param token
     * @return 解码后的Token
     */
    public static DecodedJWT parseToken(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token中包含的username信息
     * @param token
     * @return userName
     */
    public static String getUserName(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token).getClaim("userName").asString();
    }

    /**
     * 从Cookie中的token获取用户名
     * @param cookies
     * @return
     */
    public static String getUserName(Cookie[] cookies){
        for(Cookie cookie: cookies){
            if("token".equals(cookie.getName())){
                return JWTUtils.getUserName(cookie.getValue());
            }
        }
        return "";
    }

    /**
     * 验证Token
     * @param token
     * @return 验证结果，true为合法
     */
    public static boolean verifyToken(String token){
        try{
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
