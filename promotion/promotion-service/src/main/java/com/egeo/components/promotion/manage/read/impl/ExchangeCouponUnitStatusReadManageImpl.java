package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ExchangeCouponUnitStatusReadManage;
import com.egeo.components.promotion.dao.read.ExchangeCouponUnitStatusReadDAO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ExchangeCouponUnitStatusReadManageImpl implements ExchangeCouponUnitStatusReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeCouponUnitStatusReadDAO exchangeCouponUnitStatusReadDAO;
	
	public ExchangeCouponUnitStatusPO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusPO po) {
		ExchangeCouponUnitStatusPO exchangeCouponUnitStatuspo = new ExchangeCouponUnitStatusPO();
		exchangeCouponUnitStatuspo.setId(po.getId());
		return exchangeCouponUnitStatusReadDAO.findById(exchangeCouponUnitStatuspo);
	}

	public PageResult<ExchangeCouponUnitStatusPO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusPO po, Pagination page) {
		
		PageResult<ExchangeCouponUnitStatusPO> pageResult = new PageResult<ExchangeCouponUnitStatusPO>();
		List<ExchangeCouponUnitStatusPO> list = null;

		int cnt = exchangeCouponUnitStatusReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = exchangeCouponUnitStatusReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ExchangeCouponUnitStatusPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ExchangeCouponUnitStatusPO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusPO po) {

		return exchangeCouponUnitStatusReadDAO.findAll(po,null);
	}

	public List<Integer> findUnitStatusAll(ExchangeCouponUnitStatusPO po) {
		List<Integer> statusList = new ArrayList<Integer>();
		List<ExchangeCouponUnitStatusPO> all = exchangeCouponUnitStatusReadDAO.findAll(po,null);
		for (ExchangeCouponUnitStatusPO unitStatusPO : all) {
			Integer status = unitStatusPO.getAllowExchangeUnitStatus();
			statusList.add(status);
		}
		return statusList;
	}
	
}
	