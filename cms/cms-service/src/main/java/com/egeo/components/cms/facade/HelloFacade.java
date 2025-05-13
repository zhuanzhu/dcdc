package com.egeo.components.cms.facade;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.HelloReadService;


@Component
public class HelloFacade {

	@Resource
	private HelloReadService helloReadService;

	public String helloWorld() {

		return helloReadService.helloWord();
	}

}

	