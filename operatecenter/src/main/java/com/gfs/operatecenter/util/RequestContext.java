package com.gfs.operatecenter.util;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title: LocalRequestContext.java
 * Description: 当前请求上下文对象 (存放了HttpSession、HttpServletRequest等对象)
 * @author lanchangsheng
 * @date 2018-9-5
 */
@Setter
@Getter
public class RequestContext {

	public HttpServletRequest request;
	public HttpServletResponse response;

}