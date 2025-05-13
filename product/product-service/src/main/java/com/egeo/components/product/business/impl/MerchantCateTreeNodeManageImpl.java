package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantCateTreeNodeManage;
import com.egeo.components.product.facade.MerchantCateTreeNodeFacade;

@Service("merchantCateTreeNode")
public class MerchantCateTreeNodeManageImpl implements MerchantCateTreeNodeManage{

	
	@Resource(name="merchantCateTreeNodeFacade")
	private MerchantCateTreeNodeFacade merchantCateTreeNodeFacade;
	


}
	