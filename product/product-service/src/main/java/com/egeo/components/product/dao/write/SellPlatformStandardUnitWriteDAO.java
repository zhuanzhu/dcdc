package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.SellPlatformStandardUnitPO;
import com.egeo.orm.BaseWriteDAO;

public interface SellPlatformStandardUnitWriteDAO extends BaseWriteDAO<SellPlatformStandardUnitPO> {
	/**
	 * 根据suid修改比较平台信息
	 * @param sellPlatformStandardUnitPO
	 * @return
	 */
	int updateSellPlatformStandardUnitByStandardUnitIdWithTx(SellPlatformStandardUnitPO sellPlatformStandardUnitPO);
}
	