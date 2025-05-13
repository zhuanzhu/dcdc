package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SellPlatformStandardUnitRecordWriteService;
import com.egeo.components.product.manage.write.SellPlatformStandardUnitRecordWriteManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitRecordConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;

@Service("sellPlatformStandardUnitRecordWriteService")
public class SellPlatformStandardUnitRecordWriteServiceImpl  implements SellPlatformStandardUnitRecordWriteService {
	@Autowired
	private SellPlatformStandardUnitRecordWriteManage sellPlatformStandardUnitRecordWriteManage;

	@Override
	public Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
		Long rt = sellPlatformStandardUnitRecordWriteManage.insertSellPlatformStandardUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
		int rt = sellPlatformStandardUnitRecordWriteManage.updateSellPlatformStandardUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
		int rt = sellPlatformStandardUnitRecordWriteManage.deleteSellPlatformStandardUnitRecordWithTx(po);		
		return rt;
	}
}
	