package com.gdut.jiyi.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 * @author 古春波
 */
@Slf4j
public class EncodeUtil {

    private static final String ENCODE_ALGORITHM = "SHA-1";
    /**
     * 盐
     */
    private static final String SALT = "gcb";

    public static String encodeWithSha(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update((SALT + str).getBytes());

            return new BigInteger(messageDigest.digest()).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("加密出错，异常：" + e);
            return null;
        }
    }
    
}
