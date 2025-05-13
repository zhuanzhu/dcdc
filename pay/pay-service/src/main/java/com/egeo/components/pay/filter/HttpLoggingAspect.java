package com.egeo.components.pay.filter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponseWrapper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.egeo.utils.log.XLogger;

@Component
@Aspect
public class HttpLoggingAspect {

	private static final XLogger logger = XLogger.getLogger(HttpLoggingAspect.class);

	@Before("within(com.egeo..controller.*)")
	public void before(JoinPoint joinPoint) {

//		Object args[] = joinPoint.getArgs();
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//		log.info("{}.{}：2请求参数：{}", method.getDeclaringClass().getName(), method.getName(), ArrayUtils.toString(args));

		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			// 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
			Object[] objs = joinPoint.getArgs();
			String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (int i = 0; i < objs.length; i++) {
				if (!(objs[i] instanceof ExtendedServletRequestDataBinder)
						&& !(objs[i] instanceof HttpServletResponseWrapper)) {
					paramMap.put(argNames[i], objs[i]);
				}
			}
			if (paramMap.size() > 0) {
				logger.info("方法:{}.{}\t参数:{}", method.getDeclaringClass().getName(), method.getName(),
						JSON.toJSONString(paramMap));
			}
		} catch (Exception e) {
			logger.error("AOP before:", e);
		}
	}

	@AfterReturning(value = "within(com.egeo..controller.*)", returning = "rvt")
	public void after(JoinPoint joinPoint, Object rvt) {
		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			logger.info("{}.{}\t返回数据：{}", method.getDeclaringClass().getName(), method.getName(),
					JSON.toJSONString(rvt, SerializerFeature.DisableCircularReferenceDetect));
		} catch (Exception e) {
			logger.error("AOP after:", e);
		}
	}
}
