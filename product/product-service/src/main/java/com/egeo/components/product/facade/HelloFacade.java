package com.egeo.components.product.facade;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Resource
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	