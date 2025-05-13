package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponGroupReadManage;
import com.egeo.components.promotion.dao.read.CouponGroupReadDAO;
import com.egeo.components.promotion.po.CouponGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponGroupReadManageImpl implements CouponGroupReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponGroupReadDAO couponGroupReadDAO;
	
	public CouponGroupPO findCouponGroupById(CouponGroupPO po) {
		CouponGroupPO couponGrouppo = new CouponGroupPO();
		couponGrouppo.setId(po.getId());
		return couponGroupReadDAO.findById(couponGrouppo);
	}

	public PageResult<CouponGroupPO> findCouponGroupOfPage(CouponGroupPO po, Pagination page) {
		
		PageResult<CouponGroupPO> pageResult = new PageResult<CouponGroupPO>();
		List<CouponGroupPO> list = null;

		int cnt = couponGroupReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponGroupReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponGroupPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponGroupPO> findCouponGroupAll(CouponGroupPO po) {

		return couponGroupReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<CouponGroupPO> findCouponGroupOfPageByBlurry(CouponGroupPO po, Pagination page) {
		PageResult<CouponGroupPO> pageResult = new PageResult<CouponGroupPO>();
		List<CouponGroupPO> list = null;

		int cnt = couponGroupReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponGroupReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<CouponGroupPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	