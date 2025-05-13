package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProdSalesRecordWriteDAO extends BaseWriteDAO<MerchantProdSalesRecordPO> {
	/**
	 * 根据id增加销售数量
	 * @param merchantProdSalesRecordId
	 * @param salesVolume
	 * @return
	 */
	int addSalesVolumeByIdWithTx(@Param("merchantProdSalesRecordId")Long merchantProdSalesRecordId, @Param("salesVolume")Long salesVolume);
}
	