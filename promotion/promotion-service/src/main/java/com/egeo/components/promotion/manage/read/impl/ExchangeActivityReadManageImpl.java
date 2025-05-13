package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ExchangeActivityReadManage;
import com.egeo.components.promotion.dao.read.ExchangeActivityReadDAO;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ExchangeActivityReadManageImpl implements ExchangeActivityReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeActivityReadDAO exchangeActivityReadDAO;
	
	public ExchangeActivityPO findExchangeActivityById(ExchangeActivityPO po) {
		ExchangeActivityPO exchangeActivitypo = new ExchangeActivityPO();
		exchangeActivitypo.setId(po.getId());
		return exchangeActivityReadDAO.findById(exchangeActivitypo);
	}

	public PageResult<ExchangeActivityPO> findExchangeActivityOfPage(ExchangeActivityPO po, Pagination page) {
		
		PageResult<ExchangeActivityPO> pageResult = new PageResult<ExchangeActivityPO>();
		List<ExchangeActivityPO> list = null;

		int cnt = exchangeActivityReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeActivityReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ExchangeActivityPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ExchangeActivityPO> findExchangeActivityAll(ExchangeActivityPO po) {

		return exchangeActivityReadDAO.findAll(po,null);
	}

    /**
     * 模糊查询以旧换新活动列表
     * @param po
     * @param page
     * @return
     */
	public PageResult<ExchangeActivityPO> fuzzQueryExchangeActivityOfPage(ExchangeActivityPO po, Pagination page) {

		PageResult<ExchangeActivityPO> pageResult = new PageResult<ExchangeActivityPO>();
		List<ExchangeActivityPO> list = null;

		int cnt = exchangeActivityReadDAO.countOfFuzzQueryPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeActivityReadDAO.fuzzQueryExchangeActivity(po, page);
		} else {
			list = new ArrayList<ExchangeActivityPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<ExchangeActivityPO> findExchangeActivityByIds(List<Long> exchangeList) {
		return exchangeActivityReadDAO.findExchangeActivityByIds(exchangeList);
	}


}
	