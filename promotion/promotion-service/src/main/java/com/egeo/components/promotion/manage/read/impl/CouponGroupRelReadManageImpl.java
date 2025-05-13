package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponGroupRelReadManage;
import com.egeo.components.promotion.dao.read.CouponGroupRelReadDAO;
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponGroupRelReadManageImpl implements CouponGroupRelReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponGroupRelReadDAO couponGroupRelReadDAO;
	
	public CouponGroupRelPO findCouponGroupRelById(CouponGroupRelPO po) {
		CouponGroupRelPO couponGroupRelpo = new CouponGroupRelPO();
		couponGroupRelpo.setId(po.getId());
		return couponGroupRelReadDAO.findById(couponGroupRelpo);
	}

	public PageResult<CouponGroupRelPO> findCouponGroupRelOfPage(CouponGroupRelPO po, Pagination page) {
		
		PageResult<CouponGroupRelPO> pageResult = new PageResult<CouponGroupRelPO>();
		List<CouponGroupRelPO> list = null;

		int cnt = couponGroupRelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponGroupRelReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponGroupRelPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponGroupRelPO> findCouponGroupRelAll(CouponGroupRelPO po) {

		return couponGroupRelReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findCouponIdListByGroupId(Long couponGroupId) {
		return couponGroupRelReadDAO.findCouponIdListByGroupId(couponGroupId);
	}

}
	