package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.RoleFunctionTreeNodeReadManage;
import com.egeo.components.user.dao.read.RoleFunctionTreeNodeReadDAO;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class RoleFunctionTreeNodeReadManageImpl implements RoleFunctionTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleFunctionTreeNodeReadDAO roleFunctionTreeNodeReadDAO;
	
	public RoleFunctionTreeNodePO findRoleFunctionTreeNodeById(RoleFunctionTreeNodePO po) {
		RoleFunctionTreeNodePO roleFunctionTreeNodepo = new RoleFunctionTreeNodePO();
		roleFunctionTreeNodepo.setId(po.getId());
		return roleFunctionTreeNodeReadDAO.findById(roleFunctionTreeNodepo);
	}

	public PageResult<RoleFunctionTreeNodePO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodePO po, Pagination page) {
		
		PageResult<RoleFunctionTreeNodePO> pageResult = new PageResult<RoleFunctionTreeNodePO>();
		List<RoleFunctionTreeNodePO> list = null;

		int cnt = roleFunctionTreeNodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = roleFunctionTreeNodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<RoleFunctionTreeNodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<RoleFunctionTreeNodePO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodePO po) {

		return roleFunctionTreeNodeReadDAO.findAll(po,null);
	}
	
}
	