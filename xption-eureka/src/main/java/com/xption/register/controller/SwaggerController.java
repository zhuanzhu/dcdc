package com.xption.register.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContextHolder;

@Controller
@RequestMapping
public class SwaggerController {

	@RequestMapping(value = "swaggerList", method = RequestMethod.GET)
	public void swaggerList(HttpServletResponse response) {
		List<Application> list = EurekaServerContextHolder.getInstance().getServerContext().getRegistry().getSortedApplications();
		StringBuilder content = new StringBuilder();
		for (Application a : list) {
			content.append("<a href='" + a.getInstances().get(0).getHomePageUrl() + "/swagger-ui.html'>" + a.getName() + "</a></br></br>");
		}
		try {
			response.getOutputStream().write(content.toString().getBytes());
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
