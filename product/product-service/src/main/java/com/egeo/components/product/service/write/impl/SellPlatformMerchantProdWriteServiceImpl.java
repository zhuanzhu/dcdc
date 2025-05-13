package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SellPlatformMerchantProdWriteService;
import com.egeo.components.product.manage.write.SellPlatformMerchantProdWriteManage;
import com.egeo.components.product.converter.SellPlatformMerchantProdConverter;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;

@Service("sellPlatformMerchantProdWriteService")
public class SellPlatformMerchantProdWriteServiceImpl  implements SellPlatformMerchantProdWriteService {
	@Autowired
	private SellPlatformMerchantProdWriteManage sellPlatformMerchantProdWriteManage;

	@Override
	public Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
		Long rt = sellPlatformMerchantProdWriteManage.insertSellPlatformMerchantProdWithTx(po);		
		return rt;
	}

	@Override
	public int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
		int rt = sellPlatformMerchantProdWriteManage.updateSellPlatformMerchantProdWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
		int rt = sellPlatformMerchantProdWriteManage.deleteSellPlatformMerchantProdWithTx(po);		
		return rt;
	}
}
	