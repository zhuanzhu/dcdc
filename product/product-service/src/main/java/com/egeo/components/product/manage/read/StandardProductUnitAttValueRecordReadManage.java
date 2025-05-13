package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttValueRecordReadManage {

	public StandardProductUnitAttValueRecordPO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordPO po);

	public PageResult<StandardProductUnitAttValueRecordPO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordPO po,Pagination page);

	public List<StandardProductUnitAttValueRecordPO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordPO po);
}
	