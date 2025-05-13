package com.egeo.components.config.service.read.impl;

import org.springframework.stereotype.Service;

import com.egeo.components.config.service.read.HelloReadService;


@Service("helloReadService")
public class HelloReadServiceImpl implements HelloReadService {

	@Override
	public String helloWord() {
	
		return "helloWorld";
	}
	
}

	