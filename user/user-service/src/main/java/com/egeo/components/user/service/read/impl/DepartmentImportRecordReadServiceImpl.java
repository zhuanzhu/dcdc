package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.DepartmentImportRecordReadService;
import com.egeo.components.user.converter.DepartmentImportRecordConverter;
import com.egeo.components.user.dao.read.DepartmentImportRecordReadDAO;
import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.components.user.po.DepartmentImportRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentImportRecordReadService")
public class DepartmentImportRecordReadServiceImpl implements DepartmentImportRecordReadService {
	@Autowired
	private DepartmentImportRecordReadDAO departmentImportRecordReadDAO;

	@Override
	public DepartmentImportRecordDTO findDepartmentImportRecordById(DepartmentImportRecordDTO dto) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		DepartmentImportRecordPO departmentImportRecordpo = new DepartmentImportRecordPO();
		departmentImportRecordpo.setId(po.getId());
		DepartmentImportRecordPO list = departmentImportRecordReadDAO.findById(departmentImportRecordpo);	
		return DepartmentImportRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<DepartmentImportRecordDTO> findDepartmentImportRecordOfPage(DepartmentImportRecordDTO dto, Pagination page) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		
		PageResult<DepartmentImportRecordPO> pageResult = new PageResult<DepartmentImportRecordPO>();
		List<DepartmentImportRecordPO> listT = null;

		int cnt = departmentImportRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = departmentImportRecordReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<DepartmentImportRecordPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DepartmentImportRecordDTO> list = DepartmentImportRecordConverter.toDTO(pageResult.getList());
        PageResult<DepartmentImportRecordDTO> result = new PageResult<DepartmentImportRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DepartmentImportRecordDTO> findDepartmentImportRecordAll(DepartmentImportRecordDTO dto) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		List<DepartmentImportRecordPO> list =departmentImportRecordReadDAO.findAll(po,null);	
		return DepartmentImportRecordConverter.toDTO(list);
	}
}
	