package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdCauseWriteService;
import com.egeo.components.product.manage.write.MerchantProdCauseWriteManage;
import com.egeo.components.product.converter.MerchantProdCauseConverter;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.po.MerchantProdCausePO;

@Service("merchantProdCauseWriteService")
public class MerchantProdCauseWriteServiceImpl  implements MerchantProdCauseWriteService {
	@Autowired
	private MerchantProdCauseWriteManage merchantProdCauseWriteManage;

	@Override
	public Long insertMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
		Long rt = merchantProdCauseWriteManage.insertMerchantProdCauseWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
		int rt = merchantProdCauseWriteManage.updateMerchantProdCauseWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
		int rt = merchantProdCauseWriteManage.deleteMerchantProdCauseWithTx(po);		
		return rt;
	}
}
	