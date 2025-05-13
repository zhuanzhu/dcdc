package com.egeo.components.user.facade;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Resource
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	