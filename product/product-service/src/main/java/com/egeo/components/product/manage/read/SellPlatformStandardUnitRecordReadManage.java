package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformStandardUnitRecordReadManage {

	public SellPlatformStandardUnitRecordPO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordPO po);

	public PageResult<SellPlatformStandardUnitRecordPO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordPO po,Pagination page);

	public List<SellPlatformStandardUnitRecordPO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordPO po);
}
	