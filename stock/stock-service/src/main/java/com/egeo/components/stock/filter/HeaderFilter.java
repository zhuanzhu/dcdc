package com.egeo.components.stock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;

import com.egeo.components.stock.common.HeaderUtils;
import com.egeo.utils.log.XLogger;

@Configuration
public class HeaderFilter implements Filter {
	private static final XLogger logger = XLogger.getLogger(HeaderFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Exception exception = null;
		try {
			HeaderUtils.getHeaderPublicParam(httpRequest);
			chain.doFilter(httpRequest, httpResponse);
		} catch (Exception e) {
			exception = e;
			logger.error("逻辑异常！！！", e);
		}
		if (exception != null) {
			throw new ServletException(exception);
		}
	}

	@Override
	public void destroy() {

	}
}
