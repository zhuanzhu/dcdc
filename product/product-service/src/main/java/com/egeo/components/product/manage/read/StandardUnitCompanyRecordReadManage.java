package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCompanyRecordReadManage {

	public StandardUnitCompanyRecordPO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordPO po);

	public PageResult<StandardUnitCompanyRecordPO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordPO po,Pagination page);

	public List<StandardUnitCompanyRecordPO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordPO po);
}
	