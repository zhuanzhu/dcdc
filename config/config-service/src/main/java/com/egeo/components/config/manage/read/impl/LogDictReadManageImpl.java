package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.LogDictReadDAO;
import com.egeo.components.config.manage.read.LogDictReadManage;
import com.egeo.components.config.po.LogDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LogDictReadManageImpl implements LogDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogDictReadDAO logDictReadDAO;
	
	public LogDictPO findLogDictById(LogDictPO po) {
		LogDictPO logDictpo = new LogDictPO();
		logDictpo.setId(po.getId());
		return logDictReadDAO.findById(logDictpo);
	}

	public PageResult<LogDictPO> findLogDictOfPage(LogDictPO po, Pagination page) {
		
		PageResult<LogDictPO> pageResult = new PageResult<LogDictPO>();
		List<LogDictPO> list = null;

		int cnt = logDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = logDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LogDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LogDictPO> findLogDictAll(LogDictPO po) {

		return logDictReadDAO.findAll(po,null);
	}

	@Override
	public List<LogDictPO> findLogDictAllById(Long msgId) {
		return logDictReadDAO.findLogDictAllById(msgId);
	}
	
}
	