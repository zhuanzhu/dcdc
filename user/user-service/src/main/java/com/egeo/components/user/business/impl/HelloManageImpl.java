package com.egeo.components.user.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.HelloManage;
import com.egeo.components.user.facade.HelloFacade;
import com.egeo.web.JsonResult;


@Service("hello")
public class HelloManageImpl implements HelloManage {

	
	@Resource(name = "helloFacade")
	private HelloFacade helloFacade;
	
	@Override
	public JsonResult<String> helloWord() {
		JsonResult  rt  = new JsonResult();
		rt.setData(helloFacade.helloWorld());
		return rt ;
	}
	
}

	