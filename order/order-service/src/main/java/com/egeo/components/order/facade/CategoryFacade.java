package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.CategoryReadService;
import com.egeo.components.order.service.write.CategoryWriteService;


@Component
public class CategoryFacade {
	
	@Resource
	private CategoryReadService categoryReadService;
	
	@Resource
	private CategoryWriteService categoryWriteService;
	


}
	