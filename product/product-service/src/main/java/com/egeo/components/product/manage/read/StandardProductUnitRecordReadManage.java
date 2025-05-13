package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitRecordReadManage {

	public StandardProductUnitRecordPO findStandardProductUnitRecordById(StandardProductUnitRecordPO po);

	public PageResult<StandardProductUnitRecordPO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordPO po,Pagination page);

	public List<StandardProductUnitRecordPO> findStandardProductUnitRecordAll(StandardProductUnitRecordPO po);
}
	