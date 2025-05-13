package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.DepartmentImportRecordReadDAO;
import com.egeo.components.user.manage.read.DepartmentImportRecordReadManage;
import com.egeo.components.user.po.DepartmentImportRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DepartmentImportRecordReadManageImpl implements DepartmentImportRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentImportRecordReadDAO departmentImportRecordReadDAO;
	
	public DepartmentImportRecordPO findDepartmentImportRecordById(DepartmentImportRecordPO po) {
		DepartmentImportRecordPO departmentImportRecordpo = new DepartmentImportRecordPO();
		departmentImportRecordpo.setId(po.getId());
		return departmentImportRecordReadDAO.findById(departmentImportRecordpo);
	}

	public PageResult<DepartmentImportRecordPO> findDepartmentImportRecordOfPage(DepartmentImportRecordPO po, Pagination page) {
		
		PageResult<DepartmentImportRecordPO> pageResult = new PageResult<DepartmentImportRecordPO>();
		List<DepartmentImportRecordPO> list = null;

		int cnt = departmentImportRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = departmentImportRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DepartmentImportRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DepartmentImportRecordPO> findDepartmentImportRecordAll(DepartmentImportRecordPO po) {

		return departmentImportRecordReadDAO.findAll(po,null);
	}
	
}
	