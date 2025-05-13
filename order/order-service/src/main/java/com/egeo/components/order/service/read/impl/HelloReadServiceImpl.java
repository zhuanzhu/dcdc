package com.egeo.components.order.service.read.impl;

import org.springframework.stereotype.Service;
import com.egeo.components.order.service.read.HelloReadService;



@Service("helloReadService")
public class HelloReadServiceImpl  implements HelloReadService {

	@Override
	public String helloWord() {
	
		return "helloWorld";
	}
	
}

	