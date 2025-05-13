package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ReturnPicReadService;
import com.egeo.components.order.service.write.ReturnPicWriteService;


@Component
public class ReturnPicFacade {
	
	@Resource
	private ReturnPicReadService returnPicReadService;
	
	@Resource
	private ReturnPicWriteService returnPicWriteService;
	


}
	