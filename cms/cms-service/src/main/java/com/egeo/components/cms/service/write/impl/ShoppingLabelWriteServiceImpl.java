package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ShoppingLabelWriteService;
import com.egeo.components.cms.manage.write.ShoppingLabelWriteManage;
import com.egeo.components.cms.converter.ShoppingLabelConverter;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.po.ShoppingLabelPO;

@Service("shoppingLabelWriteService")
public class ShoppingLabelWriteServiceImpl  implements ShoppingLabelWriteService {
	@Autowired
	private ShoppingLabelWriteManage shoppingLabelWriteManage;

	@Override
	public Long insertShoppingLabelWithTx(ShoppingLabelDTO dto) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
		Long rt = shoppingLabelWriteManage.insertShoppingLabelWithTx(po);		
		return rt;
	}

	@Override
	public int updateShoppingLabelWithTx(ShoppingLabelDTO dto) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
		int rt = shoppingLabelWriteManage.updateShoppingLabelWithTx(po);		
		return rt;
	}

	@Override
	public int deleteShoppingLabelWithTx(ShoppingLabelDTO dto) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
		int rt = shoppingLabelWriteManage.deleteShoppingLabelWithTx(po);		
		return rt;
	}
}
	