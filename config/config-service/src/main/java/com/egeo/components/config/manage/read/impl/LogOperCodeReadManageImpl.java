package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.LogOperCodeReadDAO;
import com.egeo.components.config.manage.read.LogOperCodeReadManage;
import com.egeo.components.config.po.LogOperCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LogOperCodeReadManageImpl implements LogOperCodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogOperCodeReadDAO logOperCodeReadDAO;
	
	public LogOperCodePO findLogOperCodeById(LogOperCodePO po) {
		LogOperCodePO logOperCodepo = new LogOperCodePO();
		logOperCodepo.setId(po.getId());
		return logOperCodeReadDAO.findById(logOperCodepo);
	}

	public PageResult<LogOperCodePO> findLogOperCodeOfPage(LogOperCodePO po, Pagination page) {
		
		PageResult<LogOperCodePO> pageResult = new PageResult<LogOperCodePO>();
		List<LogOperCodePO> list = null;

		int cnt = logOperCodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = logOperCodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LogOperCodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LogOperCodePO> findLogOperCodeAll(LogOperCodePO po) {

		return logOperCodeReadDAO.findAll(po,null);
	}
	
}
	