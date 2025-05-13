package com.egeo.components.cms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@WebFilter
@Slf4j
public class SomeFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		log.debug("进入SomeFilter");
		/**
		 * 因为请求体的流一般在进入aop时就被关闭了,这里这样写可以在aop里面拿到请求体,具体参考org.springframework.web.filter.AbstractRequestLoggingFilter.
		 * 假如这个方法有更改request的操作 就把这段代码写在上面.
		 */
		if (log.isDebugEnabled()) {
			httpServletRequest = new ContentCachingRequestWrapper(httpServletRequest);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
