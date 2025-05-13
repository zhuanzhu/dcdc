package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.PlatfromMenuManage;
import com.egeo.components.order.facade.PlatfromMenuFacade;

@Service("platfromMenu")
public class PlatfromMenuManageImpl implements PlatfromMenuManage{

	
	@Resource(name="platfromMenuFacade")
	private PlatfromMenuFacade platfromMenuFacade;
	


}
	