package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProdSalesRecordReadDAO extends BaseReadDAO<MerchantProdSalesRecordPO>{
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	Long findSalesRecordByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 根据puid查询pu销售信息
	 * @param puId
	 * @return
	 */
	MerchantProdSalesRecordPO findByPUId(@Param("puId")Long puId);
}
	