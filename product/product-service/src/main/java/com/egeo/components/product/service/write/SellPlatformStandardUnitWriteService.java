package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;


public interface SellPlatformStandardUnitWriteService {

	public Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);

	public int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);

	public int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);
	/**
	 * 根据suid删除su比价平台信息
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long merchantProdId);
}
	