package com.gdut.jiyi.common;



/**
 * @author 古春波
 */
public class ResultVo<T> {

    private String code;
    private T data;
    private String msg;

    private ResultVo(){
    }
    

    public ResultVo(String code){
        this.code = code;
    }

    public ResultVo(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(String code, T data){
        this.code = code;
        this.data = data;
    }

    public ResultVo(String code, String msg, T data){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
}
