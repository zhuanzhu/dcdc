package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponStoreReadManage;
import com.egeo.components.promotion.dao.read.CouponStoreReadDAO;
import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponStoreReadManageImpl implements CouponStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponStoreReadDAO couponStoreReadDAO;
	
	public CouponStorePO findCouponStoreById(CouponStorePO po) {
		CouponStorePO couponStorepo = new CouponStorePO();
		couponStorepo.setId(po.getId());
		return couponStoreReadDAO.findById(couponStorepo);
	}

	public PageResult<CouponStorePO> findCouponStoreOfPage(CouponStorePO po, Pagination page) {
		
		PageResult<CouponStorePO> pageResult = new PageResult<CouponStorePO>();
		List<CouponStorePO> list = null;

		int cnt = couponStoreReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponStoreReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponStorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponStorePO> findCouponStoreAll(CouponStorePO po) {

		return couponStoreReadDAO.findAll(po,null);
	}

	@Override
	public Long findCouponStoreCountByCouponIdAndStoreId(CouponStorePO couponStorePO) {
		return couponStoreReadDAO.findCouponStoreCountByCouponIdAndStoreId(couponStorePO);
	}

}
	