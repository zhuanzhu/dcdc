package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentTempReadManage {

	public DepartmentTempPO findDepartmentTempById(DepartmentTempPO po);

	public PageResult<DepartmentTempPO> findDepartmentTempOfPage(DepartmentTempPO po,Pagination page);

	public List<DepartmentTempPO> findDepartmentTempAll(DepartmentTempPO po);

	public List<DepartmentTempPO> findDepartmentTempAllByIdsArr(DepartmentTempPO po, Long[] departmentTempIdsArr);
}
	