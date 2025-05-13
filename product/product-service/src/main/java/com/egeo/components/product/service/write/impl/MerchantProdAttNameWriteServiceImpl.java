package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdAttNameWriteService;
import com.egeo.components.product.manage.write.MerchantProdAttNameWriteManage;
import com.egeo.components.product.converter.MerchantProdAttNameConverter;
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.components.product.po.MerchantProdAttNamePO;

@Service("merchantProdAttNameWriteService")
public class MerchantProdAttNameWriteServiceImpl  implements MerchantProdAttNameWriteService {
	@Autowired
	private MerchantProdAttNameWriteManage merchantProdAttNameWriteManage;

	@Override
	public Long insertMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
		Long rt = merchantProdAttNameWriteManage.insertMerchantProdAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
		int rt = merchantProdAttNameWriteManage.updateMerchantProdAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
		int rt = merchantProdAttNameWriteManage.deleteMerchantProdAttNameWithTx(po);		
		return rt;
	}
}
	