package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.DepartmentTempReadService;
import com.egeo.components.user.converter.DepartmentTempConverter;
import com.egeo.components.user.dao.read.DepartmentTempReadDAO;
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.po.DepartmentTempPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentTempReadService")
public class DepartmentTempReadServiceImpl implements DepartmentTempReadService {
	@Autowired
	private DepartmentTempReadDAO departmentTempReadDAO;

	@Override
	public DepartmentTempDTO findDepartmentTempById(DepartmentTempDTO dto) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		DepartmentTempPO departmentTemppo = new DepartmentTempPO();
		departmentTemppo.setId(po.getId());
		DepartmentTempPO list = departmentTempReadDAO.findById(departmentTemppo);		
		return DepartmentTempConverter.toDTO(list);
	}

	@Override
	public PageResult<DepartmentTempDTO> findDepartmentTempOfPage(DepartmentTempDTO dto, Pagination page) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		
		PageResult<DepartmentTempPO> pageResult = new PageResult<DepartmentTempPO>();
		List<DepartmentTempPO> listT = null;

		int cnt = departmentTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = departmentTempReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<DepartmentTempPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DepartmentTempDTO> list = DepartmentTempConverter.toDTO(pageResult.getList());
        PageResult<DepartmentTempDTO> result = new PageResult<DepartmentTempDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DepartmentTempDTO> findDepartmentTempAll(DepartmentTempDTO dto) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		List<DepartmentTempPO> list = departmentTempReadDAO.findAll(po,null);		
		return DepartmentTempConverter.toDTO(list);
	}

	@Override
	public List<DepartmentTempDTO> findDepartmentTempAllByIdsArr(DepartmentTempDTO dto, Long[] departmentTempIdsArr) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		List<DepartmentTempPO> list = departmentTempReadDAO.findDepartmentTempAllByIdsArr(po,departmentTempIdsArr);
		return DepartmentTempConverter.toDTO(list);
	}
}
	