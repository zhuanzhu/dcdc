package com.egeo.components.cms.service.read.impl;

import org.springframework.stereotype.Service;
import com.egeo.components.cms.service.read.HelloReadService;



@Service("helloReadService")
public class HelloReadServiceImpl  implements HelloReadService {

	@Override
	public String helloWord() {
	
		return "helloWorld";
	}
	
}

	