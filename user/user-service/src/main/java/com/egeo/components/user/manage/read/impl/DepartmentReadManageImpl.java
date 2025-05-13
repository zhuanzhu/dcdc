package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.DepartmentReadManage;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DepartmentReadManageImpl implements DepartmentReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentReadDAO departmentReadDAO;
	
	public DepartmentPO findDepartmentById(DepartmentPO po) {
		DepartmentPO departmentpo = new DepartmentPO();
		departmentpo.setId(po.getId());
		return departmentReadDAO.findById(departmentpo);
	}

	public PageResult<DepartmentPO> findDepartmentOfPage(DepartmentPO po, Pagination page) {
		
		PageResult<DepartmentPO> pageResult = new PageResult<DepartmentPO>();
		List<DepartmentPO> list = null;

		int cnt = departmentReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = departmentReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DepartmentPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DepartmentPO> findDepartmentAll(DepartmentPO po) {
		return departmentReadDAO.findAll(po,null);
	}

	@Override
	public DepartmentPO queryDepartmentByUserId(Long userId) {
		return departmentReadDAO.queryDepartmentByUserId(userId);
	}

	@Override
	public List<DepartmentPO> queryLeafDepByCompanyId(Long companyId) {
		return departmentReadDAO.queryLeafDepByCompanyId(companyId);
	}

	@Override
	public List<DepartmentPO> querySuperDepListByCompanyId(Long companyId) {
		return departmentReadDAO.querySuperDepListByCompanyId(companyId);
	}

	@Override
	public List<DepartmentPO> queryDepsByPid(Long id) {
		return departmentReadDAO.queryDepsByPid(id);
	}

	@Override
	public List<DepartmentPO> queryDepsByCompanyId(Long companyId) {
		return departmentReadDAO.queryDepartmentByCompanyId(companyId);
	}
	
}
	