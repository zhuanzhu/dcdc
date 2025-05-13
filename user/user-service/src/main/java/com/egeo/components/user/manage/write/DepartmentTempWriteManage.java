package com.egeo.components.user.manage.write;


import java.util.List;

import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.components.user.po.DepartmentTreePO;


public interface DepartmentTempWriteManage {

	Long insertDepartmentTempWithTx(DepartmentTempPO po);

	int updateDepartmentTempWithTx(DepartmentTempPO po);

	int deleteDepartmentTempWithTx(DepartmentTempPO po);



}
	