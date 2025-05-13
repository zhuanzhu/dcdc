package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.DepartmentReadService;
import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.po.DepartmentPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("departmentReadService")
public class DepartmentReadServiceImpl implements DepartmentReadService {
	@Autowired
	private DepartmentReadDAO departmentReadDAO;

	@Override
	public DepartmentDTO findDepartmentById(DepartmentDTO dto) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		DepartmentPO departmentpo = new DepartmentPO();
		departmentpo.setId(po.getId());
		DepartmentPO list = departmentReadDAO.findById(departmentpo);		
		return DepartmentConverter.toDTO(list);
	}

	@Override
	public PageResult<DepartmentDTO> findDepartmentOfPage(DepartmentDTO dto, Pagination page) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		
		PageResult<DepartmentPO> pageResult = new PageResult<DepartmentPO>();
		List<DepartmentPO> listT = null;

		int cnt = departmentReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = departmentReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<DepartmentPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DepartmentDTO> list = DepartmentConverter.toDTO(pageResult.getList());
        PageResult<DepartmentDTO> result = new PageResult<DepartmentDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DepartmentDTO> findDepartmentAll(DepartmentDTO dto) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		List<DepartmentPO> list = departmentReadDAO.findAll(po,null);	
		return DepartmentConverter.toDTO(list);
	}

	@Override
	public List<DepartmentDTO> recursionDepartmentAll(DepartmentDTO dto) {
		DepartmentPO po = new DepartmentPO();
		po.setCompanyId(dto.getCompanyId());
		po.setPlatformId(dto.getPlatformId());
		List<DepartmentPO> list = departmentReadDAO.findAll(po,null);
		return DepartmentConverter.toDTO(list);
	}

	@Override
	public DepartmentDTO queryDepartmentByUserId(Long userId) {
		return DepartmentConverter.toDTO(departmentReadDAO.queryDepartmentByUserId(userId));
	}

	@Override
	public List<DepartmentDTO> queryLeafDepByCompanyId(Long companyId) {
		return DepartmentConverter.toDTO(departmentReadDAO.queryLeafDepByCompanyId(companyId));
	}

	@Override
	public List<DepartmentDTO> querySuperDepListByCompanyId(Long companyId) {
		return DepartmentConverter.toDTO(departmentReadDAO.querySuperDepListByCompanyId(companyId));
	}

	@Override
	public List<DepartmentDTO> queryDepsByPid(Long id) {
		return DepartmentConverter.toDTO(departmentReadDAO.queryDepsByPid(id));
	}

	@Override
	public List<DepartmentDTO> queryDepsByCompanyId(Long companyId) {
		
		return DepartmentConverter.toDTO(departmentReadDAO.queryDepartmentByCompanyId(companyId));
	}
}
	