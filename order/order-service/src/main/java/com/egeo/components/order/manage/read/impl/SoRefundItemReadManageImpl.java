package com.egeo.components.order.manage.read.impl;

import com.egeo.components.order.dao.read.SoRefundItemReadDAO;
import com.egeo.components.order.manage.read.SoRefundItemReadManage;
import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class SoRefundItemReadManageImpl implements SoRefundItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SoRefundItemReadDAO soRefundItemReadDAO;

	@Override
	public SoRefundItemPO findSoRefundItemById(SoRefundItemPO po) {
		SoRefundItemPO soRefundItemPO = new SoRefundItemPO();
		soRefundItemPO.setId(po.getId());
		return soRefundItemReadDAO.findById(soRefundItemPO);
	}

	@Override
	public PageResult<SoRefundItemPO> findSoRefundItemOfPage(SoRefundItemPO po, Pagination page) {
		
		PageResult<SoRefundItemPO> pageResult = new PageResult<SoRefundItemPO>();
		List<SoRefundItemPO> list = null;

		int cnt = soRefundItemReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soRefundItemReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoRefundItemPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<SoRefundItemPO> findSoRefundItemAll(SoRefundItemPO po) {

		return soRefundItemReadDAO.findAll(po,null);
	}
	
}
	