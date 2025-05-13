package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.DepartmentImportRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentImportRecordReadManage {

	public DepartmentImportRecordPO findDepartmentImportRecordById(DepartmentImportRecordPO po);

	public PageResult<DepartmentImportRecordPO> findDepartmentImportRecordOfPage(DepartmentImportRecordPO po,Pagination page);

	public List<DepartmentImportRecordPO> findDepartmentImportRecordAll(DepartmentImportRecordPO po);
}
	