package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitClientRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitClientRecordReadManage {

	public StandardUnitClientRecordPO findStandardUnitClientRecordById(StandardUnitClientRecordPO po);

	public PageResult<StandardUnitClientRecordPO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordPO po,Pagination page);

	public List<StandardUnitClientRecordPO> findStandardUnitClientRecordAll(StandardUnitClientRecordPO po);
}
	