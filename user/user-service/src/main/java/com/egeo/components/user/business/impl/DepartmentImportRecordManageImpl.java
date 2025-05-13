package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.DepartmentImportRecordManage;
import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.components.user.facade.DepartmentImportRecordFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentImportRecord")
public class DepartmentImportRecordManageImpl implements DepartmentImportRecordManage{

	
	@Resource(name="departmentImportRecordFacade")
	private DepartmentImportRecordFacade departmentImportRecordFacade;

	@Override
	public DepartmentImportRecordDTO findDepartmentImportRecordById(DepartmentImportRecordDTO dto) {
		return departmentImportRecordFacade.findDepartmentImportRecordById(dto);
	}

	@Override
	public PageResult<DepartmentImportRecordDTO> findDepartmentImportRecordOfPage(DepartmentImportRecordDTO dto, Pagination page) {
		return departmentImportRecordFacade.findDepartmentImportRecordOfPage(dto, page);
	}

	@Override
	public List<DepartmentImportRecordDTO> findDepartmentImportRecordAll(DepartmentImportRecordDTO dto) {
		return departmentImportRecordFacade.findDepartmentImportRecordAll(dto);
	}

	@Override
	public Long insertDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		return departmentImportRecordFacade.insertDepartmentImportRecordWithTx(dto);
	}

	@Override
	public int updateDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		return departmentImportRecordFacade.updateDepartmentImportRecordWithTx(dto);
	}

	@Override
	public int deleteDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		return departmentImportRecordFacade.deleteDepartmentImportRecordWithTx(dto);
	}


}
	