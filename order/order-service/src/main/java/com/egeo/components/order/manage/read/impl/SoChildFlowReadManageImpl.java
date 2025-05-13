package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoChildFlowReadManage;
import com.egeo.components.order.dao.read.SoChildFlowReadDAO;
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoChildFlowReadManageImpl implements SoChildFlowReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoChildFlowReadDAO soChildFlowReadDAO;
	
	public SoChildFlowPO findSoChildFlowById(SoChildFlowPO po) {
		SoChildFlowPO soChildFlowpo = new SoChildFlowPO();
		soChildFlowpo.setId(po.getId());
		return soChildFlowReadDAO.findById(soChildFlowpo);
	}

	public PageResult<SoChildFlowPO> findSoChildFlowOfPage(SoChildFlowPO po, Pagination page) {
		
		PageResult<SoChildFlowPO> pageResult = new PageResult<SoChildFlowPO>();
		List<SoChildFlowPO> list = null;

		int cnt = soChildFlowReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soChildFlowReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoChildFlowPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoChildFlowPO> findSoChildFlowAll(SoChildFlowPO po) {

		return soChildFlowReadDAO.findAll(po,null);
	}

	@Override
	public List<SoChildFlowPO> queryFlowListBySoId(Long orderId) {
		return soChildFlowReadDAO.queryFlowListBySoId(orderId);
	}

	@Override
	public List<SoChildFlowPO> queryFlowListBySoChildId(Long soChildId) {
		return soChildFlowReadDAO.queryFlowListBySoChildId(soChildId);
	}
	
}
	