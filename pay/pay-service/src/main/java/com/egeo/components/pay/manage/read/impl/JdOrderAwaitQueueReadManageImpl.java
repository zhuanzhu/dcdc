package com.egeo.components.pay.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.JdOrderAwaitQueueReadDAO;
import com.egeo.components.pay.manage.read.JdOrderAwaitQueueReadManage;
import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdOrderAwaitQueueReadManageImpl implements JdOrderAwaitQueueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdOrderAwaitQueueReadDAO jdOrderAwaitQueueReadDAO;
	
	public JdOrderAwaitQueuePO findJdOrderAwaitQueueById(JdOrderAwaitQueuePO po) {
		JdOrderAwaitQueuePO jdOrderAwaitQueuepo = new JdOrderAwaitQueuePO();
		jdOrderAwaitQueuepo.setId(po.getId());
		return jdOrderAwaitQueueReadDAO.findById(jdOrderAwaitQueuepo);
	}

	public PageResult<JdOrderAwaitQueuePO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueuePO po, Pagination page) {
		
		PageResult<JdOrderAwaitQueuePO> pageResult = new PageResult<JdOrderAwaitQueuePO>();
		List<JdOrderAwaitQueuePO> list = null;

		int cnt = jdOrderAwaitQueueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdOrderAwaitQueueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdOrderAwaitQueuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdOrderAwaitQueuePO> findJdOrderAwaitQueueAll(JdOrderAwaitQueuePO po) {

		return jdOrderAwaitQueueReadDAO.findAll(po,null);
	}
	
}
	