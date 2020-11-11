package com.gdut.jiyi.util;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * redis操作工具类
 */
public class RedisUtil {
    
    /**
     * the prefix of this project in redis database
     */
    private static final String REDIS_PROJECT_PREFIX = "jiyi.";
    private static final String USER_ID_KEY = "ui";
    private static final String SESSION_KEY_PREFIX = "sn.";
    /**
     * 验证码前缀
     */
    private static final String IMG_CODE_PREFIX = "ic.";
    /**
     * 手机验证码
     */
    private static final String PHONE_CODE_PREFIX = "pc.";
    /**
     * 重置密码手机验证码
     */
    private static final String RESET_PASS_PHONE_CODE_PREFIX = "rsppc.";

    private static final String INIT_USER_ID = "0";


    private static final Integer SESSION_EXPIRE_SECONDES =  300000 * 60;
    /**
     * 验证码有效时间
     */
    private static final Integer IMG_CODE_SECONDES =  30 * 60;

    private static final Integer PHONE_CODE_SECONDES =  5 * 60;
    /**
     * 重置密码手机验证码有效时间
     */
    private static final Integer RESET_PASS_PHONE_CODE_SECONDES =  5 * 60;

    /**
     * 获取用户id
     * @param valueOperations valueOperations
     * @return UserId
     */
    public static String getUserId(ValueOperations<String, Object> valueOperations){
        //若没有，则插入默认的用户id
        valueOperations.setIfAbsent( REDIS_PROJECT_PREFIX + USER_ID_KEY,INIT_USER_ID);
        return String.valueOf(valueOperations.increment(REDIS_PROJECT_PREFIX + USER_ID_KEY));
    }

  

    /**
     * 设置全局session，用于分布式集群，值为用户id
     * @param hashOperations hashOperations
     * @param userId session中存有用户的id
     * 过期时间为30 * 60 秒
     */
    public static void setSessionUserId(HashOperations<String, String , Object> hashOperations,String globalCookieValue,String userId){
        if (hashOperations == null || userId == null || globalCookieValue == null){
            throw new IllegalArgumentException("set session value error");
        }
        hashOperations.put(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX +
                globalCookieValue, USER_ID_KEY, userId);
        hashOperations.getOperations().expire(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX +
                globalCookieValue, SESSION_EXPIRE_SECONDES,SECONDS);
    }


    /**
     * 获取用户id
     * @param hashOperations hashOperations
     * @param globalCookieValue globalCookieValue
     * @return  用户id
     */
    public static String getSessionUserId(HashOperations<String, String, Object> hashOperations,String globalCookieValue){
        if (hashOperations == null || globalCookieValue == null){ return null; }
        return (String) hashOperations.get(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX +
                        globalCookieValue , USER_ID_KEY);
    }

    public static void delSessionUserId(HashOperations<String,String,Object> hashOperations,String globalCookieValue){
        if (hashOperations == null || globalCookieValue == null){return ; }
        hashOperations.delete(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX +
                globalCookieValue ,USER_ID_KEY);
    }



    /**
     * 更新session过期时间
     * @param hashOperations hashOperations
     * @param globalCookieValue globalCookieValue
     */
    public static void upDateSessionExpireTime(HashOperations<String,String,Object> hashOperations,String globalCookieValue){
        if (hashOperations == null || globalCookieValue == null){
            return ;
        }
        Boolean hasSession = hashOperations.getOperations().hasKey(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX + globalCookieValue);
        if (hasSession!= null){
            hashOperations.getOperations().expire(REDIS_PROJECT_PREFIX + SESSION_KEY_PREFIX + globalCookieValue,SESSION_EXPIRE_SECONDES,SECONDS);
        }
    }

    /**
     * 将图片验证码设置到redis缓存中
     * 半小时失效
     * @param valueOperations valueOperations
     * @param code code
     * @param cookieVal cookieVal
     */
    public static void setImgCode(ValueOperations<String,Object> valueOperations, String code,String cookieVal){
        if (valueOperations == null || code == null || cookieVal == null){
            return ;
        }
        valueOperations.set(REDIS_PROJECT_PREFIX + IMG_CODE_PREFIX+cookieVal,code, Duration.ofSeconds(IMG_CODE_SECONDES));
    }

    /**
     * 从redis缓存中取出图片验证码的值
     */
    public static String getImgCode(ValueOperations<String,Object> valueOperations,String cookieVal){
        if (valueOperations == null || cookieVal == null){
            return null;
        }
        return String.valueOf(valueOperations.get(REDIS_PROJECT_PREFIX + IMG_CODE_PREFIX + cookieVal));
    }

    /**
     * 删除图片验证码
     */
    public static void delImgCode(ValueOperations<String,Object> valueOperations,String cookieVal){
        if (valueOperations == null || cookieVal == null){
            return ;
        }
         valueOperations.getOperations().delete(REDIS_PROJECT_PREFIX + IMG_CODE_PREFIX + cookieVal);
    }



    /**
     * 写入手机号验证码
     * @param valueOperations valueOperations
     * @param phoneCode phoneCode
     * @param cookieVal cookieVal
     */
    public static Integer setPhoneCode(ValueOperations<String,Object> valueOperations, String phoneCode,String cookieVal){
        if (valueOperations == null || phoneCode == null || cookieVal == null){
            return PHONE_CODE_SECONDES;
        }
        valueOperations.set(REDIS_PROJECT_PREFIX + PHONE_CODE_PREFIX + cookieVal,phoneCode, Duration.ofSeconds(PHONE_CODE_SECONDES));
        return PHONE_CODE_SECONDES;
    }

    /**
     * 获取手机验证码
     * @param valueOperations valueOperations
     * @param cookieVal cookieVal
     * @return 机验证码
     */
    public static String getPhoneCode(ValueOperations<String,Object> valueOperations,String cookieVal){
        if (valueOperations == null || cookieVal == null){
            return null;
        }
        return String.valueOf(valueOperations.get(REDIS_PROJECT_PREFIX + PHONE_CODE_PREFIX + cookieVal));
    }

    /**
     * 删除手机验证码
     * @param valueOperations valueOperations
     * @param cookieVal cookieVal
     */
    public static void delPhoneCode(ValueOperations<String,Object> valueOperations,String cookieVal){
        valueOperations.getOperations().delete(REDIS_PROJECT_PREFIX + PHONE_CODE_PREFIX + cookieVal);
    }

    /**
     * 重置密码时发送 
     * @param valueOperations valueOperations
     * @param phoneCode phoneCode
     * @return 手机号验证码
     */
    public static Map<String,String> setResetPassPhoneCode(ValueOperations<String,Object> valueOperations, String phoneCode){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("expire",RESET_PASS_PHONE_CODE_SECONDES + "");
        valueOperations.set(REDIS_PROJECT_PREFIX + RESET_PASS_PHONE_CODE_PREFIX + token,phoneCode,Duration.ofSeconds(RESET_PASS_PHONE_CODE_SECONDES));
        return map;
    }

    /**
     * 重置密码时发送手机号验证码
     * @param valueOperations valueOperations
     * @return 机号验证码
     */
    public static String getResetPassPhoneCode(ValueOperations<String,Object> valueOperations,String token){
        return (String) valueOperations.get(REDIS_PROJECT_PREFIX + RESET_PASS_PHONE_CODE_PREFIX + token);
    }



}
