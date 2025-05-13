package com.egeo.components.third.filter;

import com.egeo.components.third.common.HeaderUtils;
import com.egeo.utils.log.XLogger;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
