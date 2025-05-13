package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordReadManage {

	public StandardUnitRecordPO findStandardUnitRecordById(StandardUnitRecordPO po);

	public PageResult<StandardUnitRecordPO> findStandardUnitRecordOfPage(StandardUnitRecordPO po,Pagination page);

	public List<StandardUnitRecordPO> findStandardUnitRecordAll(StandardUnitRecordPO po);
}
	