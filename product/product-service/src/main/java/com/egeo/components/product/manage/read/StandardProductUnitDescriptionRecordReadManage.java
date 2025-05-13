package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitDescriptionRecordReadManage {

	public StandardProductUnitDescriptionRecordPO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordPO po);

	public PageResult<StandardProductUnitDescriptionRecordPO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordPO po,Pagination page);

	public List<StandardProductUnitDescriptionRecordPO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordPO po);
}
	