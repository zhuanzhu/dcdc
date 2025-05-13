package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SellPlatformStandardUnitWriteService;
import com.egeo.components.product.manage.write.SellPlatformStandardUnitWriteManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitPO;

@Service("sellPlatformStandardUnitWriteService")
public class SellPlatformStandardUnitWriteServiceImpl  implements SellPlatformStandardUnitWriteService {
	@Autowired
	private SellPlatformStandardUnitWriteManage sellPlatformStandardUnitWriteManage;

	@Override
	public Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
		Long rt = sellPlatformStandardUnitWriteManage.insertSellPlatformStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
		int rt = sellPlatformStandardUnitWriteManage.updateSellPlatformStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
		int rt = sellPlatformStandardUnitWriteManage.deleteSellPlatformStandardUnitWithTx(po);		
		return rt;
	}
	/**
	 * 根据suid删除su比价平台信息
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		// TODO Auto-generated method stub
		return sellPlatformStandardUnitWriteManage.deleteByStandardUnitIdWithTx(merchantProdId);
	}
}
	