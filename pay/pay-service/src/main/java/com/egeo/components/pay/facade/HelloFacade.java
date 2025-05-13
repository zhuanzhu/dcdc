package com.egeo.components.pay.facade;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.pay.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Resource
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	