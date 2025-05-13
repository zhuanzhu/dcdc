package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.CartWriteService;
import com.egeo.components.order.manage.write.CartWriteManage;
import com.egeo.components.order.converter.CartConverter;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.po.CartPO;

@Service("cartWriteService")
public class CartWriteServiceImpl  implements CartWriteService {
	@Autowired
	private CartWriteManage cartWriteManage;

	@Override
	public Long insertCartWithTx(CartDTO dto) {
		CartPO po = CartConverter.toPO(dto);
		Long rt = cartWriteManage.insertCartWithTx(po);		
		return rt;
	}

	@Override
	public int updateCartWithTx(CartDTO dto) {
		CartPO po = CartConverter.toPO(dto);
		int rt = cartWriteManage.updateCartWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCartWithTx(CartDTO dto) {
		CartPO po = CartConverter.toPO(dto);
		int rt = cartWriteManage.deleteCartWithTx(po);		
		return rt;
	}
}
	