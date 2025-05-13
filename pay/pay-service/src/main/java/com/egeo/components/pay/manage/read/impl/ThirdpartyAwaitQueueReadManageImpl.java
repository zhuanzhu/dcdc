package com.egeo.components.pay.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.ThirdpartyAwaitQueueReadDAO;
import com.egeo.components.pay.manage.read.ThirdpartyAwaitQueueReadManage;
import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ThirdpartyAwaitQueueReadManageImpl implements ThirdpartyAwaitQueueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ThirdpartyAwaitQueueReadDAO thirdpartyAwaitQueueReadDAO;
	
	public ThirdpartyAwaitQueuePO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueuePO po) {
		ThirdpartyAwaitQueuePO thirdpartyAwaitQueuepo = new ThirdpartyAwaitQueuePO();
		thirdpartyAwaitQueuepo.setId(po.getId());
		return thirdpartyAwaitQueueReadDAO.findById(thirdpartyAwaitQueuepo);
	}

	public PageResult<ThirdpartyAwaitQueuePO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueuePO po, Pagination page) {
		
		PageResult<ThirdpartyAwaitQueuePO> pageResult = new PageResult<ThirdpartyAwaitQueuePO>();
		List<ThirdpartyAwaitQueuePO> list = null;

		int cnt = thirdpartyAwaitQueueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = thirdpartyAwaitQueueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ThirdpartyAwaitQueuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ThirdpartyAwaitQueuePO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueuePO po) {

		return thirdpartyAwaitQueueReadDAO.findAll(po,null);
	}
	
}
	