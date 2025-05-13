package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitDescribeRecordReadManage {

	public StandardUnitDescribeRecordPO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordPO po);

	public PageResult<StandardUnitDescribeRecordPO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordPO po,Pagination page);

	public List<StandardUnitDescribeRecordPO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordPO po);
}
	