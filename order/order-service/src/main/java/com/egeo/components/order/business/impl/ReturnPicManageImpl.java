package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ReturnPicManage;
import com.egeo.components.order.facade.ReturnPicFacade;

@Service("returnPic")
public class ReturnPicManageImpl implements ReturnPicManage{

	
	@Resource(name="returnPicFacade")
	private ReturnPicFacade returnPicFacade;
	


}
	