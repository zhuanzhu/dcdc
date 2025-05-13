package com.egeo.components.stock.service.read.impl;

import org.springframework.stereotype.Service;
import com.egeo.components.stock.service.read.HelloReadService;



@Service("helloReadService")
public class HelloReadServiceImpl  implements HelloReadService {

	@Override
	public String helloWord() {
	
		return "helloWorld";
	}
	
}

	