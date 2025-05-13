package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.DepartmentRelationReadService;
import com.egeo.components.user.converter.DepartmentRelationConverter;
import com.egeo.components.user.dao.read.DepartmentRelationReadDAO;
import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.components.user.po.DepartmentRelationPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentRelationReadService")
public class DepartmentRelationReadServiceImpl implements DepartmentRelationReadService {
	@Autowired
	private DepartmentRelationReadDAO departmentRelationReadDAO;

	@Override
	public DepartmentRelationDTO findDepartmentRelationById(DepartmentRelationDTO dto) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		DepartmentRelationPO departmentRelationpo = new DepartmentRelationPO();
		departmentRelationpo.setId(po.getId());
		DepartmentRelationPO list =departmentRelationReadDAO.findById(departmentRelationpo);		
		return DepartmentRelationConverter.toDTO(list);
	}

	@Override
	public PageResult<DepartmentRelationDTO> findDepartmentRelationOfPage(DepartmentRelationDTO dto, Pagination page) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		
		PageResult<DepartmentRelationPO> pageResult = new PageResult<DepartmentRelationPO>();
		List<DepartmentRelationPO> listT = null;

		int cnt = departmentRelationReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = departmentRelationReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<DepartmentRelationPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DepartmentRelationDTO> list = DepartmentRelationConverter.toDTO(pageResult.getList());
        PageResult<DepartmentRelationDTO> result = new PageResult<DepartmentRelationDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DepartmentRelationDTO> findDepartmentRelationAll(DepartmentRelationDTO dto) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		List<DepartmentRelationPO> list = departmentRelationReadDAO.findAll(po,null);	
		return DepartmentRelationConverter.toDTO(list);
	}
}
	