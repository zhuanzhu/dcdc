package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SellPlatformReadManage;
import com.egeo.components.product.dao.read.SellPlatformReadDAO;
import com.egeo.components.product.po.SellPlatformPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SellPlatformReadManageImpl implements SellPlatformReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformReadDAO sellPlatformReadDAO;
	
	public SellPlatformPO findSellPlatformById(SellPlatformPO po) {
		SellPlatformPO sellPlatformpo = new SellPlatformPO();
		sellPlatformpo.setId(po.getId());
		return sellPlatformReadDAO.findById(sellPlatformpo);
	}

	public PageResult<SellPlatformPO> findSellPlatformOfPage(SellPlatformPO po, Pagination page) {
		
		PageResult<SellPlatformPO> pageResult = new PageResult<SellPlatformPO>();
		List<SellPlatformPO> list = null;

		int cnt = sellPlatformReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sellPlatformReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SellPlatformPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SellPlatformPO> findSellPlatformAll(SellPlatformPO po) {

		return sellPlatformReadDAO.findAll(po,null);
	}
	
}
	