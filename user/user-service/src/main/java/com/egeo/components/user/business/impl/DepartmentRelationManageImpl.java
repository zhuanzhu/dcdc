package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.DepartmentRelationManage;
import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.components.user.facade.DepartmentRelationFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentRelation")
public class DepartmentRelationManageImpl implements DepartmentRelationManage{

	
	@Resource(name="departmentRelationFacade")
	private DepartmentRelationFacade departmentRelationFacade;

	@Override
	public DepartmentRelationDTO findDepartmentRelationById(DepartmentRelationDTO dto) {
		return departmentRelationFacade.findDepartmentRelationById(dto);
	}

	@Override
	public PageResult<DepartmentRelationDTO> findDepartmentRelationOfPage(DepartmentRelationDTO dto, Pagination page) {
		return departmentRelationFacade.findDepartmentRelationOfPage(dto, page);
	}

	@Override
	public List<DepartmentRelationDTO> findDepartmentRelationAll(DepartmentRelationDTO dto) {
		return departmentRelationFacade.findDepartmentRelationAll(dto);
	}

	@Override
	public Long insertDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		return departmentRelationFacade.insertDepartmentRelationWithTx(dto);
	}

	@Override
	public int updateDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		return departmentRelationFacade.updateDepartmentRelationWithTx(dto);
	}

	@Override
	public int deleteDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		return departmentRelationFacade.deleteDepartmentRelationWithTx(dto);
	}


}
	