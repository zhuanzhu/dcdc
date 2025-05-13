package com.egeo.components.promotion.manage.read.impl;

import com.egeo.components.promotion.dao.read.BuyCardItemRefundReadDAO;
import com.egeo.components.promotion.manage.read.BuyCardItemRefundReadManage;
import com.egeo.components.promotion.po.BuyCardItemRefundPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuyCardItemRefundReadManageImpl implements BuyCardItemRefundReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardItemRefundReadDAO buyCardItemRefundReadDAO;

	@Override
	public BuyCardItemRefundPO findBuyCardItemRefundById(BuyCardItemRefundPO po) {
		BuyCardItemRefundPO BuyCardItemRefundpo = new BuyCardItemRefundPO();
		BuyCardItemRefundpo.setId(po.getId());
		return buyCardItemRefundReadDAO.findById(BuyCardItemRefundpo);
	}

	@Override
	public PageResult<BuyCardItemRefundPO> findBuyCardItemRefundOfPage(BuyCardItemRefundPO po, Pagination page) {

		PageResult<BuyCardItemRefundPO> pageResult = new PageResult<BuyCardItemRefundPO>();
		List<BuyCardItemRefundPO> list = null;

		int cnt = buyCardItemRefundReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = buyCardItemRefundReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BuyCardItemRefundPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<BuyCardItemRefundPO> findBuyCardItemRefundAll(BuyCardItemRefundPO po) {

		return buyCardItemRefundReadDAO.findAll(po,null);
	}

}
