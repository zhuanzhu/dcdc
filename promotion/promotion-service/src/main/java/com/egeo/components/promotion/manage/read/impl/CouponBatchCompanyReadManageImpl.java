package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponBatchCompanyReadManage;
import com.egeo.components.promotion.dao.read.CouponBatchCompanyReadDAO;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponBatchCompanyReadManageImpl implements CouponBatchCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponBatchCompanyReadDAO couponBatchCompanyReadDAO;
	
	public CouponBatchCompanyPO findCouponBatchCompanyById(CouponBatchCompanyPO po) {
		CouponBatchCompanyPO couponBatchCompanypo = new CouponBatchCompanyPO();
		couponBatchCompanypo.setId(po.getId());
		return couponBatchCompanyReadDAO.findById(couponBatchCompanypo);
	}

	public PageResult<CouponBatchCompanyPO> findCouponBatchCompanyOfPage(CouponBatchCompanyPO po, Pagination page) {
		
		PageResult<CouponBatchCompanyPO> pageResult = new PageResult<CouponBatchCompanyPO>();
		List<CouponBatchCompanyPO> list = null;

		int cnt = couponBatchCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponBatchCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponBatchCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponBatchCompanyPO> findCouponBatchCompanyAll(CouponBatchCompanyPO po) {

		return couponBatchCompanyReadDAO.findAll(po,null);
	}
	
}
	