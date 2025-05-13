package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SellPlatformWriteService;
import com.egeo.components.product.manage.write.SellPlatformWriteManage;
import com.egeo.components.product.converter.SellPlatformConverter;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.po.SellPlatformPO;

@Service("sellPlatformWriteService")
public class SellPlatformWriteServiceImpl  implements SellPlatformWriteService {
	@Autowired
	private SellPlatformWriteManage sellPlatformWriteManage;

	@Override
	public Long insertSellPlatformWithTx(SellPlatformDTO dto) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
		Long rt = sellPlatformWriteManage.insertSellPlatformWithTx(po);		
		return rt;
	}

	@Override
	public int updateSellPlatformWithTx(SellPlatformDTO dto) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
		int rt = sellPlatformWriteManage.updateSellPlatformWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSellPlatformWithTx(SellPlatformDTO dto) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
		int rt = sellPlatformWriteManage.deleteSellPlatformWithTx(po);		
		return rt;
	}
}
	