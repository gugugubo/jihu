package com.gdut.jiyi.advice;


import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.common.ServiceException;
import com.gdut.jiyi.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.util.List;
import javax.validation.ConstraintViolationException;


@Slf4j
@RestControllerAdvice(basePackages = { "com.gdut.jiyi.controller" })
public class ControllerExceptionAdvice {


    @ExceptionHandler(ServiceException.class)
    private ResultVo<Object> handleServiceException(ServiceException exception) {
        String message = exception.getLocalizedMessage();
        return ResultUtil.errorWithMsg(message);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    private ResultVo<Object> handleRuntimeException(BindException exception) {
        List<ObjectError> errors = exception.getAllErrors();

        StringBuilder errorMsg = new StringBuilder();

        for (ObjectError oe : errors){
            if (errorMsg.length() > 0){
                errorMsg.append("&");
            }
            errorMsg.append(oe.getDefaultMessage());
        }
        return ResultUtil.errorWithMsg(errorMsg.toString());
    }

    /**
     * 参数校验绑定异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    private ResultVo<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String[] errors = ex.getMessage().split(",");
        StringBuilder stb = new StringBuilder();
        for (String es : errors){
            if (stb.length() > 0){
                stb.append("&");
            }
            es = es.substring(es.indexOf(":")+2);
            stb.append(es);
        }
        return ResultUtil.errorWithMsg(stb.toString());
    }

    @ExceptionHandler({RuntimeException.class})
    private ResultVo<Object> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        log.error("系统未知异常："+ex.getMessage());
        return ResultUtil.errorWithMsg("异常拼命修复中");
    }

    @ExceptionHandler({Exception.class})
    private ResultVo<Object> handleException(RuntimeException ex) {
        ex.printStackTrace();
        log.error("系统未知异常："+ex.getMessage());
        return ResultUtil.errorWithMsg("异常拼命修复中");
    }

    @ExceptionHandler({JedisConnectionException.class})
    private ResultVo<Object> handleJedisConnectionException(JedisConnectionException ex) {
        ex.printStackTrace();
        log.error("系统未知异常："+ex.getMessage());
        return ResultUtil.errorWithMsg("redis连接超时，请稍后重试");
    }

}
