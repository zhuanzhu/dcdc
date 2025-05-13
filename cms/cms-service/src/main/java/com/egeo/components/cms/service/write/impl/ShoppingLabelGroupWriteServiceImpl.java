package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ShoppingLabelGroupWriteService;
import com.egeo.components.cms.manage.write.ShoppingLabelGroupWriteManage;
import com.egeo.components.cms.converter.ShoppingLabelGroupConverter;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.po.ShoppingLabelGroupPO;

@Service("shoppingLabelGroupWriteService")
public class ShoppingLabelGroupWriteServiceImpl  implements ShoppingLabelGroupWriteService {
	@Autowired
	private ShoppingLabelGroupWriteManage shoppingLabelGroupWriteManage;

	@Override
	public Long insertShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
		Long rt = shoppingLabelGroupWriteManage.insertShoppingLabelGroupWithTx(po);		
		return rt;
	}

	@Override
	public int updateShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
		int rt = shoppingLabelGroupWriteManage.updateShoppingLabelGroupWithTx(po);		
		return rt;
	}

	@Override
	public int deleteShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
		int rt = shoppingLabelGroupWriteManage.deleteShoppingLabelGroupWithTx(po);		
		return rt;
	}
}
	