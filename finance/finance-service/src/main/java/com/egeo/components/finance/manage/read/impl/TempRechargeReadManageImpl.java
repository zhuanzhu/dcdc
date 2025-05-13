package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.TempRechargeReadDAO;
import com.egeo.components.finance.manage.read.TempRechargeReadManage;
import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class TempRechargeReadManageImpl implements TempRechargeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TempRechargeReadDAO tempRechargeReadDAO;
	
	public TempRechargePO findTempRechargeById(TempRechargePO po) {
		TempRechargePO tempRechargepo = new TempRechargePO();
		tempRechargepo.setId(po.getId());
		return tempRechargeReadDAO.findById(tempRechargepo);
	}

	public PageResult<TempRechargePO> findTempRechargeOfPage(TempRechargePO po, Pagination page) {
		
		PageResult<TempRechargePO> pageResult = new PageResult<TempRechargePO>();
		List<TempRechargePO> list = null;

		int cnt = tempRechargeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = tempRechargeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<TempRechargePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<TempRechargePO> findTempRechargeAll(TempRechargePO po) {

		return tempRechargeReadDAO.findAll(po,null);
	}

	@Override
	public double queryTempRechargeSummaryBySn(String sn) {
		return tempRechargeReadDAO.queryTempRechargeSummaryBySn(sn);
	}

	@Override
	public List<TempRechargePO> queryTempRechargeBySn(String sn) {
		return tempRechargeReadDAO.queryTempRechargeBySn(sn);
	}

	@Override
	public int queryTempRechargeCountBySn(String sn) {
		return tempRechargeReadDAO.queryTempRechargeCountBySn(sn);
	}
	
}
	