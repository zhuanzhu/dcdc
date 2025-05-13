package com.egeo.components.promotion.manage.read.impl;

import com.egeo.components.promotion.dao.read.BuyCardItemReadDAO;
import com.egeo.components.promotion.manage.read.BuyCardItemReadManage;
import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuyCardItemReadManageImpl implements BuyCardItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardItemReadDAO buyCardItemReadDAO;

	@Override
	public BuyCardItemPO findBuyCardItemById(BuyCardItemPO po) {
		BuyCardItemPO BuyCardItempo = new BuyCardItemPO();
		BuyCardItempo.setId(po.getId());
		return buyCardItemReadDAO.findById(BuyCardItempo);
	}

	@Override
	public PageResult<BuyCardItemPO> findBuyCardItemOfPage(BuyCardItemPO po, Pagination page) {

		PageResult<BuyCardItemPO> pageResult = new PageResult<BuyCardItemPO>();
		List<BuyCardItemPO> list = null;

		int cnt = buyCardItemReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = buyCardItemReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BuyCardItemPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<BuyCardItemPO> findBuyCardItemAll(BuyCardItemPO po) {

		return buyCardItemReadDAO.findAll(po,null);
	}

}
