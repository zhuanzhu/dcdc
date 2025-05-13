package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PointsReadManage;
import com.egeo.components.promotion.dao.read.PointsReadDAO;
import com.egeo.components.promotion.po.PointsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PointsReadManageImpl implements PointsReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PointsReadDAO pointsReadDAO;
	
	public PointsPO findPointsById(PointsPO po) {
		PointsPO pointspo = new PointsPO();
		pointspo.setId(po.getId());
		return pointsReadDAO.findById(pointspo);
	}

	public PageResult<PointsPO> findPointsOfPage(PointsPO po, Pagination page) {
		
		PageResult<PointsPO> pageResult = new PageResult<PointsPO>();
		List<PointsPO> list = null;

		int cnt = pointsReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = pointsReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PointsPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PointsPO> findPointsAll(PointsPO po) {

		return pointsReadDAO.findAll(po,null);
	}
	
}
	