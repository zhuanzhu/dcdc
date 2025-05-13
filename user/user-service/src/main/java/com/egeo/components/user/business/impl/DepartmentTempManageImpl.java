package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.DepartmentTempManage;
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.facade.DepartmentTempFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentTemp")
public class DepartmentTempManageImpl implements DepartmentTempManage{

	
	@Resource(name="departmentTempFacade")
	private DepartmentTempFacade departmentTempFacade;

	@Override
	public DepartmentTempDTO findDepartmentTempById(DepartmentTempDTO dto) {
		return departmentTempFacade.findDepartmentTempById(dto);
	}

	@Override
	public PageResult<DepartmentTempDTO> findDepartmentTempOfPage(DepartmentTempDTO dto, Pagination page) {
		return departmentTempFacade.findDepartmentTempOfPage(dto, page);
	}

	@Override
	public List<DepartmentTempDTO> findDepartmentTempAll(DepartmentTempDTO dto) {
		return departmentTempFacade.findDepartmentTempAll(dto);
	}

	@Override
	public Long insertDepartmentTempWithTx(DepartmentTempDTO dto) {
		return departmentTempFacade.insertDepartmentTempWithTx(dto);
	}

	@Override
	public int updateDepartmentTempWithTx(DepartmentTempDTO dto) {
		return departmentTempFacade.updateDepartmentTempWithTx(dto);
	}

	@Override
	public int deleteDepartmentTempWithTx(DepartmentTempDTO dto) {
		return departmentTempFacade.deleteDepartmentTempWithTx(dto);
	}


}
	