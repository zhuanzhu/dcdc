package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitTagRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitTagRecordReadManage {

	public StandardUnitTagRecordPO findStandardUnitTagRecordById(StandardUnitTagRecordPO po);

	public PageResult<StandardUnitTagRecordPO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordPO po,Pagination page);

	public List<StandardUnitTagRecordPO> findStandardUnitTagRecordAll(StandardUnitTagRecordPO po);
}
	