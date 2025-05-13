package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SellPlatformStandardUnitReadManage;
import com.egeo.components.product.dao.read.SellPlatformStandardUnitReadDAO;
import com.egeo.components.product.po.SellPlatformStandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SellPlatformStandardUnitReadManageImpl implements SellPlatformStandardUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformStandardUnitReadDAO sellPlatformStandardUnitReadDAO;
	
	public SellPlatformStandardUnitPO findSellPlatformStandardUnitById(SellPlatformStandardUnitPO po) {
		SellPlatformStandardUnitPO sellPlatformStandardUnitpo = new SellPlatformStandardUnitPO();
		sellPlatformStandardUnitpo.setId(po.getId());
		return sellPlatformStandardUnitReadDAO.findById(sellPlatformStandardUnitpo);
	}

	public PageResult<SellPlatformStandardUnitPO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitPO po, Pagination page) {
		
		PageResult<SellPlatformStandardUnitPO> pageResult = new PageResult<SellPlatformStandardUnitPO>();
		List<SellPlatformStandardUnitPO> list = null;

		int cnt = sellPlatformStandardUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sellPlatformStandardUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SellPlatformStandardUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SellPlatformStandardUnitPO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitPO po) {

		return sellPlatformStandardUnitReadDAO.findAll(po,null);
	}
	
}
	