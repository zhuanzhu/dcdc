package com.egeo.components.cms.business.impl;
	

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.ShoppingLabelManage;
import com.egeo.components.cms.facade.ShoppingLabelFacade;

@Service("shoppingLabel")
public class ShoppingLabelManageImpl implements ShoppingLabelManage{

	
	@Resource(name="shoppingLabelFacade")
	private ShoppingLabelFacade shoppingLabelFacade;

}
	