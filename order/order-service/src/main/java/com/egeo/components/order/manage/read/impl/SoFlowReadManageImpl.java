package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoFlowReadManage;
import com.egeo.components.order.dao.read.SoFlowReadDAO;
import com.egeo.components.order.po.SoFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoFlowReadManageImpl implements SoFlowReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoFlowReadDAO soFlowReadDAO;
	
	public SoFlowPO findSoFlowById(SoFlowPO po) {
		SoFlowPO soFlowpo = new SoFlowPO();
		soFlowpo.setId(po.getId());
		return soFlowReadDAO.findById(soFlowpo);
	}

	public PageResult<SoFlowPO> findSoFlowOfPage(SoFlowPO po, Pagination page) {
		
		PageResult<SoFlowPO> pageResult = new PageResult<SoFlowPO>();
		List<SoFlowPO> list = null;

		int cnt = soFlowReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soFlowReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoFlowPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoFlowPO> findSoFlowAll(SoFlowPO po) {
		return soFlowReadDAO.findAll(po,null);
	}

	@Override
	public List<SoFlowPO> queryFlowListBySoId(Long orderId) {
		return soFlowReadDAO.queryFlowListBySoId(orderId);
	}
	
}
	