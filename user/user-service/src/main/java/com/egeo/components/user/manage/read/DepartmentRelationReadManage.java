package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.DepartmentRelationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentRelationReadManage {

	public DepartmentRelationPO findDepartmentRelationById(DepartmentRelationPO po);

	public PageResult<DepartmentRelationPO> findDepartmentRelationOfPage(DepartmentRelationPO po,Pagination page);

	public List<DepartmentRelationPO> findDepartmentRelationAll(DepartmentRelationPO po);
}
	