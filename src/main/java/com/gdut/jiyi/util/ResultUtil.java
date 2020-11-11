package com.gdut.jiyi.util;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.emum.ResultCode;

import static com.gdut.jiyi.emum.ResultCode.*;


/**
 * 返回结果，工具类
 * @author 古春波
 */
public class ResultUtil {

    /**
     *  返回信息的成功请求
     */
    public static ResultVo<String> success(){
        return new ResultVo<>(SUCCESS.getCode(),SUCCESS.getMsg(),"");
    }

    /**
     * 返回带有数据的成功请求
     */
    public static  <T> ResultVo<T> successWithData(T data){
        return new ResultVo<>(SUCCESS.getCode(),"",data);
    }

    /**
     * 返回带有数据和消息的成功请求
     */
    public static  ResultVo<String>  successWitMsg(String msg){
        return new ResultVo<>(SUCCESS.getCode(), msg,"");
    }

    /**
     * 自定义成功请求
     */
    public static  <T> ResultVo<T> successWithAll(String msg,T data){
        return new ResultVo<>(SUCCESS.getCode(),msg,data);
    }

    /**
     * 返回带有错误消息的成功请求
     */
    public static ResultVo<String> error(){
        return new ResultVo<>(ERROR.getCode(),ERROR.getMsg(),"");
    }

    /**
     * 返回带有数据的失败请求
     */
    public static<T> ResultVo<T> errorWithData(T data){
        return new ResultVo<>(ERROR.getCode(),"",data);
    }

    /**
     * 返回带有错误信息和数据的失败请求
     * @return
     */
    public static ResultVo<Object> errorWithMsg(String msg){
        return new ResultVo<>(ERROR.getCode(),msg,"");
    }

    /**
     * 返回带有错误信息和数据的失败请求
     */
    public static  ResultVo<String>  UNauthorIZED(String msg){
        return new ResultVo<>(UNauthorIZED.getCode(),msg,"");
    }
    
    /**
     * 返回自定义数据格式的失败请求
     */
    public static <T> ResultVo<T>  errorWithAll(String msg,T data){
        return new ResultVo<>(ERROR.getCode(),msg,data);
    }

    public static  ResultVo<String>  other(ResultCode code){
        return new  ResultVo<> (code.getCode(),code.getMsg(),"");
    }
}
