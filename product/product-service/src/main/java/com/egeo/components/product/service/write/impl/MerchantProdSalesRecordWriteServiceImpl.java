package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdSalesRecordWriteService;
import com.egeo.components.product.manage.write.MerchantProdSalesRecordWriteManage;
import com.egeo.components.product.converter.MerchantProdSalesRecordConverter;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.po.MerchantProdSalesRecordPO;

@Service("merchantProdSalesRecordWriteService")
public class MerchantProdSalesRecordWriteServiceImpl  implements MerchantProdSalesRecordWriteService {
	@Autowired
	private MerchantProdSalesRecordWriteManage merchantProdSalesRecordWriteManage;

	@Override
	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
		Long rt = merchantProdSalesRecordWriteManage.insertMerchantProdSalesRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
		int rt = merchantProdSalesRecordWriteManage.updateMerchantProdSalesRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
		int rt = merchantProdSalesRecordWriteManage.deleteMerchantProdSalesRecordWithTx(po);		
		return rt;
	}

	@Override
	public int addSalesVolumeByIdWithTx(Long merchantProdSalesRecordId, Long salesVolume) {
		return merchantProdSalesRecordWriteManage.addSalesVolumeByIdWithTx(merchantProdSalesRecordId, salesVolume);
	}
}
	