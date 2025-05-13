package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ExchangeOrderRecordReadManage;
import com.egeo.components.promotion.dao.read.ExchangeOrderRecordReadDAO;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ExchangeOrderRecordReadManageImpl implements ExchangeOrderRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeOrderRecordReadDAO exchangeOrderRecordReadDAO;
	
	public ExchangeOrderRecordPO findExchangeOrderRecordById(ExchangeOrderRecordPO po) {
		ExchangeOrderRecordPO exchangeOrderRecordpo = new ExchangeOrderRecordPO();
		exchangeOrderRecordpo.setId(po.getId());
		return exchangeOrderRecordReadDAO.findById(exchangeOrderRecordpo);
	}

	public PageResult<ExchangeOrderRecordPO> findExchangeOrderRecordOfPage(ExchangeOrderRecordPO po, Pagination page) {
		
		PageResult<ExchangeOrderRecordPO> pageResult = new PageResult<ExchangeOrderRecordPO>();
		List<ExchangeOrderRecordPO> list = null;

		int cnt = exchangeOrderRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeOrderRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ExchangeOrderRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ExchangeOrderRecordPO> findExchangeOrderRecordAll(ExchangeOrderRecordPO po) {

		return exchangeOrderRecordReadDAO.findAll(po,null);
	}

	public int getCountOfCompletedOrderByExchangeId(Long exchangeId) {

		return exchangeOrderRecordReadDAO.getCountOfCompletedOrderByExchangeId(exchangeId);
	}

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeOrderRecordPO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordPO po,String startTime,String endTime, Pagination page) {

		PageResult<ExchangeOrderRecordPO> pageResult = new PageResult<ExchangeOrderRecordPO>();
		List<ExchangeOrderRecordPO> list = null;

		int cnt = exchangeOrderRecordReadDAO.countOfFuzzQueryPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeOrderRecordReadDAO.fuzzQueryExchangeOrderRecord(po,startTime,endTime, page);
		} else {
			list = new ArrayList<ExchangeOrderRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<ExchangeOrderRecordPO> findExchangeOrderRecordLived(ExchangeOrderRecordPO po) {
		return exchangeOrderRecordReadDAO.findAllLived(po,null);
	}
}
	