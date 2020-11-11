package com.gdut.jiyi.util;

/**
 * 正则匹配工具类
 */
public class RegUtil {

    /**
     * 手机号正则表达式
     */
    private static final String PHONE_REG = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
    private static final String PIC_REG = "^\\S+\\.(?i)(jpg|png)";
    private static final String VEDIO_REG="\\S+\\.(?i)(avi|mp4)$";
    private static final String AUDIO_REG="\\S+\\.(?i)(mp3|mp4)$";
    /**
     * 手机号正则表达式验证
     * @param phoneNumber 手机号
     * @return boolean
     */
    public static boolean phoneReg(String phoneNumber){
        return phoneNumber.matches(PHONE_REG);
    }

    /**
     * 判空
     * @param string string
     * @return boolean
     */
    public static boolean isEmpty(String string){
        if (null == string){
            return true;
        }
        return  string.trim().length() == 0;
    }

    public static boolean isPic(String picName){
        if (null == picName){
            return false;
        }
        return picName.matches(PIC_REG);
    }

    public static boolean isVedio(String vedioName){
        if (null == vedioName){
            return false;
        }
        return vedioName.matches(VEDIO_REG);
    }

    public static boolean isAudio(String originalFilename) {
        if (null == originalFilename){
            return false;
        }

        return originalFilename.matches(AUDIO_REG);
    }
}

