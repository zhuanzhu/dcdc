package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponReadManage;
import com.egeo.components.promotion.dao.read.CouponReadDAO;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponReadManageImpl implements CouponReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponReadDAO couponReadDAO;
	
	public CouponPO findCouponById(CouponPO po) {
		CouponPO couponpo = new CouponPO();
		couponpo.setId(po.getId());
		return couponReadDAO.findById(couponpo);
	}

	public PageResult<CouponPO> findCouponOfPage(CouponPO po, Pagination page) {
		
		PageResult<CouponPO> pageResult = new PageResult<CouponPO>();
		List<CouponPO> list = null;

		int cnt = couponReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponPO> findCouponAll(CouponPO po) {

		return couponReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<CouponPO> findCouponOfPageByBlurry(CouponPO po, List<Long> couponIdList, Pagination page) {
		PageResult<CouponPO> pageResult = new PageResult<CouponPO>();
		List<CouponPO> list = null;

		int cnt = couponReadDAO.countOfPageByBlurry(po, couponIdList);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponReadDAO.findOfPageByBlurry(po, couponIdList, page);
		} else {
			list = new ArrayList<CouponPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<CouponPO> findCouponByCouponGroupId(Long couponRelId) {
		return couponReadDAO.findCouponByCouponGroupId(couponRelId);
	}
	
}
	