package com.egeo.components.stock.facade;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Resource
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	