package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttNameRecordReadManage {

	public StandardProductUnitAttNameRecordPO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordPO po);

	public PageResult<StandardProductUnitAttNameRecordPO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordPO po,Pagination page);

	public List<StandardProductUnitAttNameRecordPO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordPO po);
}
	