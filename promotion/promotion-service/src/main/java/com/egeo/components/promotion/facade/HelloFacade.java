package com.egeo.components.promotion.facade;


import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Autowired
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	