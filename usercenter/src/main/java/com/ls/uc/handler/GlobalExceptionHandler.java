package com.ls.uc.handler;

import com.dw.sc.common.bean.ResponseHead;
import com.dw.sc.common.bean.ResponseMsg;
import com.dw.sc.common.enums.ResponseHeadEnum;
import com.dw.sc.common.exception.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
//@ConditionalOnWebApplication
//@ConditionalOnMissingBean(GlobalExceptionHandler.class)
public class GlobalExceptionHandler {

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;


    /**
     * 业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg defultBusiExceptionHandler(HttpServletRequest request, BusiException e) {
        log.error("Business Exceptions URL:{} , Error Message: {} ", request.getRequestURL(), e.getMessage());
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), String.valueOf(e.getCode()), e.getMessage(),"info");
    }

    /**
     * 其他系统异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg defultExcepitonHandler(HttpServletRequest request, Exception e) {
        log.error("System.Exceptions URL:{}", request.getRequestURL(), e);
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), ResponseHeadEnum.FAIL.getCode(), "System Exceptions","error");
    }

    /**
     * 404错误
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg notFoundExcepitonHandler(HttpServletRequest request, Exception e) {
        log.error("System.Exceptions URL:{}", request.getRequestURL(), e);
        String msg = e.getMessage();
        // 生产环境
        if (ENV_PROD.equals(profile)) {
            // 用户展示
            msg = "找不到请求";
        }
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), ResponseHeadEnum.FAIL.getCode(), msg,"error");
    }

    /**
     * 请求方法之类错误
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg requestExcepitonHandler(HttpServletRequest request, Exception e) {
        log.error("System.Exceptions URL:{}", request.getRequestURL(), e);
        String msg = e.getMessage();
        // 生产环境
        if (ENV_PROD.equals(profile)) {
            // 用户展示
            msg = "请求错误";
        }
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), ResponseHeadEnum.FAIL.getCode(), msg,"error");
    }

    /**
     * 请求方法之类错误
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg handleBindException(HttpServletRequest request, BindException e) {
        String msg = wrapperBindingResult(e.getBindingResult());
        log.error("System.Exceptions URL:{}, Msg:{}", request.getRequestURL(), msg, e);
        // 生产环境
        if (ENV_PROD.equals(profile)) {
            // 用户展示
            msg = "请求参数绑定错误";
        }
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), ResponseHeadEnum.FAIL.getCode(), msg,"error");
    }

    /**
     * 请求方法之类错误
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg handleValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String msg = wrapperBindingResult(e.getBindingResult());
        log.error("System.Exceptions URL:{}, Msg:{}", request.getRequestURL(), msg, e);
        // 生产环境
        if (ENV_PROD.equals(profile)) {
            // 用户展示
            msg = "请求参数错误";
        }
        return getResponseMsg(ResponseHeadEnum.FAIL.getStatus(), ResponseHeadEnum.FAIL.getCode(), msg,"error");
    }

    /**
     * 包装绑定异常结果
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private String wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return msg.substring(2);
    }

    private ResponseMsg getResponseMsg(Object message, Object code, String desc, String tips) {
        ResponseHead responseHead = new ResponseHead();
        ResponseMsg responseMsg = new ResponseMsg();
        responseHead.setRespStatus((String) message);
        responseHead.setRespCode((String) code);
        responseHead.setRespDesc(desc);
        responseHead.setRespTips(tips);
        responseMsg.setData(null);
        responseMsg.setHead(responseHead);
        return responseMsg;
    }

}
