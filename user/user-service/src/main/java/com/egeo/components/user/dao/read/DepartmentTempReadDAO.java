package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.orm.BaseReadDAO;

public interface DepartmentTempReadDAO extends BaseReadDAO<DepartmentTempPO>{

	List<DepartmentTempPO> findDepartmentTempAllByIdsArr(@Param("po")DepartmentTempPO po,@Param("departmentTempIdsArr") Long[] departmentTempIdsArr);
}
	