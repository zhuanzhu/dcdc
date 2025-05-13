package com.egeo.components.order.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.web.JsonResult;


/**
 * 全局异常处理
 * 
 * @author lxm
 *
 */
@RestController
@ControllerAdvice
public class GlobalExceptionAspect {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionAspect.class);

	@ExceptionHandler(value = Exception.class)
	public JsonResult errorHandler(Exception e) {

		String userData = "";
		if(RuntimeContext.cacheUser()!=null) {
			userData = JSON.toJSONString(RuntimeContext.cacheUser());
		}
		logger.error("出现异常，操作用户数据",userData);
		if (e instanceof BusinessException){
			BusinessException se = (BusinessException) e;
			logger.error(se.getMessage());
			return JsonResult.fail(se.getMessage(),se.getCode());
		}else{
			logger.error("系统异常",e);
			return JsonResult.fail("系统异常",500);
		}
	}
}
