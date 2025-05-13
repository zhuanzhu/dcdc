package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.dto.DepartmentTreeDTO;


public interface DepartmentTempWriteService {

	public Long insertDepartmentTempWithTx(DepartmentTempDTO dto);

	public int updateDepartmentTempWithTx(DepartmentTempDTO dto);

	public int deleteDepartmentTempWithTx(DepartmentTempDTO dto);

	/**
	 * 将树插入到数据库
	 * @param sortList
	 * @param platformId 
	 * @param companyId 
	 * @return
	 */
	public String insertDepartmentTempPlusWithTx(List<DepartmentTreeDTO> sortList, Long companyId, Long platformId);

}
	