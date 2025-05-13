package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantWriteService;
import com.egeo.components.product.manage.write.MerchantWriteManage;
import com.egeo.components.product.converter.MerchantConverter;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.po.MerchantPO;

@Service("merchantWriteService")
public class MerchantWriteServiceImpl  implements MerchantWriteService {
	@Autowired
	private MerchantWriteManage merchantWriteManage;

	@Override
	public Long insertMerchantWithTx(MerchantDTO dto) {
		MerchantPO po = MerchantConverter.toPO(dto);
		Long rt = merchantWriteManage.insertMerchantWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantWithTx(MerchantDTO dto) {
		MerchantPO po = MerchantConverter.toPO(dto);
		int rt = merchantWriteManage.updateMerchantWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantWithTx(MerchantDTO dto) {
		MerchantPO po = MerchantConverter.toPO(dto);
		int rt = merchantWriteManage.deleteMerchantWithTx(po);		
		return rt;
	}
}
	