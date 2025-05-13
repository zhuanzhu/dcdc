package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.DepartmentTempReadManage;
import com.egeo.components.user.dao.read.DepartmentTempReadDAO;
import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DepartmentTempReadManageImpl implements DepartmentTempReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentTempReadDAO departmentTempReadDAO;
	
	public DepartmentTempPO findDepartmentTempById(DepartmentTempPO po) {
		DepartmentTempPO departmentTemppo = new DepartmentTempPO();
		departmentTemppo.setId(po.getId());
		return departmentTempReadDAO.findById(departmentTemppo);
	}

	public PageResult<DepartmentTempPO> findDepartmentTempOfPage(DepartmentTempPO po, Pagination page) {
		
		PageResult<DepartmentTempPO> pageResult = new PageResult<DepartmentTempPO>();
		List<DepartmentTempPO> list = null;

		int cnt = departmentTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = departmentTempReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DepartmentTempPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DepartmentTempPO> findDepartmentTempAll(DepartmentTempPO po) {

		return departmentTempReadDAO.findAll(po,null);
	}

	@Override
	public List<DepartmentTempPO> findDepartmentTempAllByIdsArr(DepartmentTempPO po, Long[] departmentTempIdsArr) {
		
		return departmentTempReadDAO.findDepartmentTempAllByIdsArr(po,departmentTempIdsArr);
	}
	
}
	