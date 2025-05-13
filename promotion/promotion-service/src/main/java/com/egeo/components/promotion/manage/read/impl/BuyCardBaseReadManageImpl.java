package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.BuyCardBaseReadManage;
import com.egeo.components.promotion.dao.read.BuyCardBaseReadDAO;
import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BuyCardBaseReadManageImpl implements BuyCardBaseReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardBaseReadDAO buyCardBaseReadDAO;

	@Override
	public BuyCardBasePO findBuyCardBaseById(BuyCardBasePO po) {
		BuyCardBasePO BuyCardBasepo = new BuyCardBasePO();
		BuyCardBasepo.setId(po.getId());
		return buyCardBaseReadDAO.findById(BuyCardBasepo);
	}

	@Override
	public PageResult<BuyCardBasePO> findBuyCardBaseOfPage(BuyCardBasePO po, Pagination page) {

		PageResult<BuyCardBasePO> pageResult = new PageResult<>();
		List<BuyCardBasePO> list = null;

		int cnt = buyCardBaseReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = buyCardBaseReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<BuyCardBasePO> findBuyCardBaseAll(BuyCardBasePO po) {

		return buyCardBaseReadDAO.findAll(po,null);
	}

	@Override
	public int findMaxSortValue(){
		return buyCardBaseReadDAO.findMaxSortValue();
	}
}
