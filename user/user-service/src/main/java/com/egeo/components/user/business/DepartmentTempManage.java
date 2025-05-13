package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentTempManage {

	public DepartmentTempDTO findDepartmentTempById(DepartmentTempDTO dto);	

	public PageResult<DepartmentTempDTO> findDepartmentTempOfPage(DepartmentTempDTO dto,Pagination page);

	public List<DepartmentTempDTO> findDepartmentTempAll(DepartmentTempDTO dto);

	Long insertDepartmentTempWithTx(DepartmentTempDTO dto);

	int updateDepartmentTempWithTx(DepartmentTempDTO dto);

	int deleteDepartmentTempWithTx(DepartmentTempDTO dto);

}
	