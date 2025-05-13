package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.condition.LogCondition;
import com.egeo.components.config.dao.read.LogReadDAO;
import com.egeo.components.config.manage.read.LogReadManage;
import com.egeo.components.config.po.LogPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LogReadManageImpl implements LogReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogReadDAO logReadDAO;
	
	public LogPO findLogById(LogPO po) {
		LogPO logpo = new LogPO();
		logpo.setId(po.getId());
		return logReadDAO.findById(logpo);
	}

	public PageResult<LogPO> findLogOfPage(LogPO po, Pagination page) {
		
		PageResult<LogPO> pageResult = new PageResult<LogPO>();
		List<LogPO> list = null;

		int cnt = logReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = logReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LogPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LogPO> findLogAll(LogPO po) {

		return logReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<LogCondition> findLogInfoOfPage(LogCondition po, Pagination page) {
		
		PageResult<LogCondition> pageResult = new PageResult<LogCondition>();
		List<LogCondition> list = null;

		int cnt = logReadDAO.countLogInfoOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = logReadDAO.findLogInfoOfPage(po, page);
		} else {
			list = new ArrayList<LogCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	