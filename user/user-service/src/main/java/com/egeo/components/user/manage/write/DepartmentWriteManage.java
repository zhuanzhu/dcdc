package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.DepartmentPO;


public interface DepartmentWriteManage {

	Long insertDepartmentWithTx(DepartmentPO po);

	int updateDepartmentWithTx(DepartmentPO po);

	int deleteDepartmentWithTx(DepartmentPO po);

	Long deleteByCompanyIdWithTx(DepartmentPO po);
}
	