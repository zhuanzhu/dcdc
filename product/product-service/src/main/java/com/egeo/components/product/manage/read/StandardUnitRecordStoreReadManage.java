package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitRecordStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordStoreReadManage {

	public StandardUnitRecordStorePO findStandardUnitRecordStoreById(StandardUnitRecordStorePO po);

	public PageResult<StandardUnitRecordStorePO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStorePO po,Pagination page);

	public List<StandardUnitRecordStorePO> findStandardUnitRecordStoreAll(StandardUnitRecordStorePO po);
}
	