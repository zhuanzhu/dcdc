package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SellPlatformStandardUnitPO;


public interface SellPlatformStandardUnitWriteManage {

	Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po);

	int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po);

	int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po);
	/**
	 * 根据suid删除su比价平台信息
	 * @param merchantProdId
	 * @return
	 */
	int deleteByStandardUnitIdWithTx(Long merchantProdId);
	/**
	 * 根据suid和比价平台id修改比较平台信息
	 * @param sellPlatformStandardUnitPO
	 * @return
	 */
	int updateSellPlatformStandardUnitByStandardUnitIdWithTx(SellPlatformStandardUnitPO sellPlatformStandardUnitPO);
}
	