package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.FuCoinHistoryReadManage;
import com.egeo.components.promotion.dao.read.FuCoinHistoryReadDAO;
import com.egeo.components.promotion.po.FuCoinHistoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FuCoinHistoryReadManageImpl implements FuCoinHistoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuCoinHistoryReadDAO fuCoinHistoryReadDAO;
	
	public FuCoinHistoryPO findFuCoinHistoryById(FuCoinHistoryPO po) {
		FuCoinHistoryPO fuCoinHistorypo = new FuCoinHistoryPO();
		fuCoinHistorypo.setId(po.getId());
		return fuCoinHistoryReadDAO.findById(fuCoinHistorypo);
	}

	public PageResult<FuCoinHistoryPO> findFuCoinHistoryOfPage(FuCoinHistoryPO po, Pagination page) {
		
		PageResult<FuCoinHistoryPO> pageResult = new PageResult<FuCoinHistoryPO>();
		List<FuCoinHistoryPO> list = null;

		int cnt = fuCoinHistoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = fuCoinHistoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FuCoinHistoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FuCoinHistoryPO> findFuCoinHistoryAll(FuCoinHistoryPO po) {

		return fuCoinHistoryReadDAO.findAll(po,null);
	}
	
}
	