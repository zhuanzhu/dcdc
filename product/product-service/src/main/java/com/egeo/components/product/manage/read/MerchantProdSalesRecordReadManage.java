package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdSalesRecordReadManage {

	public MerchantProdSalesRecordPO findMerchantProdSalesRecordById(MerchantProdSalesRecordPO po);

	public PageResult<MerchantProdSalesRecordPO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordPO po,Pagination page);

	public List<MerchantProdSalesRecordPO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordPO po);
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	public Long findSalesRecordByStandardUnitId(Long standardUnitId);
	/**
	 * 根据puid查询pu销售信息
	 * @param puId
	 * @return
	 */
	public MerchantProdSalesRecordPO findByPUId(Long puId);
}
	