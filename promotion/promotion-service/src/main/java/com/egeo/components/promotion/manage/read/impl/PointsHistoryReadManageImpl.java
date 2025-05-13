package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PointsHistoryReadManage;
import com.egeo.components.promotion.dao.read.PointsHistoryReadDAO;
import com.egeo.components.promotion.po.PointsHistoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PointsHistoryReadManageImpl implements PointsHistoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PointsHistoryReadDAO pointsHistoryReadDAO;
	
	public PointsHistoryPO findPointsHistoryById(PointsHistoryPO po) {
		PointsHistoryPO pointsHistorypo = new PointsHistoryPO();
		pointsHistorypo.setId(po.getId());
		return pointsHistoryReadDAO.findById(pointsHistorypo);
	}

	public PageResult<PointsHistoryPO> findPointsHistoryOfPage(PointsHistoryPO po, Pagination page) {
		
		PageResult<PointsHistoryPO> pageResult = new PageResult<PointsHistoryPO>();
		List<PointsHistoryPO> list = null;

		int cnt = pointsHistoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = pointsHistoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PointsHistoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PointsHistoryPO> findPointsHistoryAll(PointsHistoryPO po) {

		return pointsHistoryReadDAO.findAll(po,null);
	}
	
}
	