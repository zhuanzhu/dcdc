package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ExchangeBatchReadManage;
import com.egeo.components.promotion.dao.read.ExchangeBatchReadDAO;
import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ExchangeBatchReadManageImpl implements ExchangeBatchReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeBatchReadDAO exchangeBatchReadDAO;
	
	public ExchangeBatchPO findExchangeBatchById(ExchangeBatchPO po) {
		ExchangeBatchPO exchangeBatchpo = new ExchangeBatchPO();
		exchangeBatchpo.setId(po.getId());
		return exchangeBatchReadDAO.findById(exchangeBatchpo);
	}

	public PageResult<ExchangeBatchPO> findExchangeBatchOfPage(ExchangeBatchPO po, Pagination page) {
		
		PageResult<ExchangeBatchPO> pageResult = new PageResult<ExchangeBatchPO>();
		List<ExchangeBatchPO> list = null;

		int cnt = exchangeBatchReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeBatchReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ExchangeBatchPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ExchangeBatchPO> findExchangeBatchAll(ExchangeBatchPO po) {

		return exchangeBatchReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> checkIsShowExchange(Long batchId, Integer unitStatus) {

		return exchangeBatchReadDAO.checkIsShowExchange(new Date(),batchId,unitStatus);
	}

	@Override
	public List<Long> findExchangeIdsByBatch(ExchangeBatchPO exchangeBatchPO) {
		return exchangeBatchReadDAO.findExchangeIdsByBatch(exchangeBatchPO);
	}

	@Override
	public List<Long> findExchangeActivityByOldCouponUnitId(Long batchId, Integer couponUnitStatus) {
		return exchangeBatchReadDAO.findExchangeActivityByOldCouponUnitId(batchId, couponUnitStatus);
	}

}
	