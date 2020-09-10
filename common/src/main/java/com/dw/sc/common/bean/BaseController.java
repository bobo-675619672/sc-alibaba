package com.dw.sc.common.bean;

import com.dw.sc.common.enums.ResponseHeadEnum;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServlet;

/**
 * @author liweicheng
 */
@SuppressWarnings("unchecked")
public class BaseController<T> extends HttpServlet {

    public ResponseMsg success() {
        return success(null);
    }

    /**
     * 请求成功返回 （固定成功返回）
     */
    public ResponseMsg success(Object obj) {
        ResponseMsg responseMsg = new ResponseMsg();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRespStatus(ResponseHeadEnum.SUCCEE.getStatus());
        responseHead.setRespCode(ResponseHeadEnum.SUCCEE.getCode());
        responseHead.setRespDesc(ResponseHeadEnum.SUCCEE.getMessage());
        responseMsg.setHead(responseHead);
        responseMsg.setData(obj);
        return responseMsg;
    }

    /**
     * 请求失败返回 （自定义信息）
     */
    public ResponseMsg fail(String code, String message) {
        ResponseMsg responseMsg = new ResponseMsg<>();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRespStatus(ResponseHeadEnum.FAIL.getStatus());
        responseHead.setRespCode(code);
        responseHead.setRespDesc(message);
        responseMsg.setHead(responseHead);
        return responseMsg;
    }

    /**
     * 请求失败返回 （校验返回）
     */
    public ResponseMsg fail(BindingResult errors) {
        ResponseHead responseHead = new ResponseHead();
        ResponseMsg responseMsg = new ResponseMsg();
        errors.getAllErrors().forEach(error -> {
            responseHead.setRespStatus(ResponseHeadEnum.FAIL.getStatus());
            responseHead.setRespCode(ResponseHeadEnum.FAIL.getCode());
            responseHead.setRespDesc(error.getDefaultMessage());
        });
        responseMsg.setHead(responseHead);
        return responseMsg;
    }

}