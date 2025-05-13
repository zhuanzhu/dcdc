package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponGroupStoreReadManage;
import com.egeo.components.promotion.dao.read.CouponGroupStoreReadDAO;
import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponGroupStoreReadManageImpl implements CouponGroupStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponGroupStoreReadDAO couponGroupStoreReadDAO;
	
	public CouponGroupStorePO findCouponGroupStoreById(CouponGroupStorePO po) {
		CouponGroupStorePO couponGroupStorepo = new CouponGroupStorePO();
		couponGroupStorepo.setId(po.getId());
		return couponGroupStoreReadDAO.findById(couponGroupStorepo);
	}

	public PageResult<CouponGroupStorePO> findCouponGroupStoreOfPage(CouponGroupStorePO po, Pagination page) {
		
		PageResult<CouponGroupStorePO> pageResult = new PageResult<CouponGroupStorePO>();
		List<CouponGroupStorePO> list = null;

		int cnt = couponGroupStoreReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponGroupStoreReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponGroupStorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponGroupStorePO> findCouponGroupStoreAll(CouponGroupStorePO po) {

		return couponGroupStoreReadDAO.findAll(po,null);
	}
	
}
	