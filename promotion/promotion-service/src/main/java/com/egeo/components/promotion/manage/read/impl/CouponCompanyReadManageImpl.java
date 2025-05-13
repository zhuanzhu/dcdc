package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.NormalConstant;
import com.egeo.components.promotion.dao.read.CouponCompanyReadDAO;
import com.egeo.components.promotion.dao.read.CouponGroupRelReadDAO;
import com.egeo.components.promotion.manage.read.CouponCompanyReadManage;
import com.egeo.components.promotion.po.CouponCompanyPO;
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponCompanyReadManageImpl implements CouponCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponCompanyReadDAO couponCompanyReadDAO;
	
	@Autowired
	private CouponGroupRelReadDAO couponGroupRelReadDAO;
	
	public CouponCompanyPO findCouponCompanyById(CouponCompanyPO po) {
		CouponCompanyPO couponCompanypo = new CouponCompanyPO();
		couponCompanypo.setId(po.getId());
		return couponCompanyReadDAO.findById(couponCompanypo);
	}

	public PageResult<CouponCompanyPO> findCouponCompanyOfPage(CouponCompanyPO po, Pagination page) {
		
		PageResult<CouponCompanyPO> pageResult = new PageResult<CouponCompanyPO>();
		List<CouponCompanyPO> list = null;

		int cnt = couponCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponCompanyPO> findCouponCompanyAll(CouponCompanyPO po) {

		return couponCompanyReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findCompanyByCouponIdOrCouponGroupId(Long couponId, Long couponGroupId, List<Long> officialList,
			List<Long> competitiveList, List<Long> testList) {
		List<Long> list = new ArrayList<Long>();
		if (couponGroupId != null) {
			// 优惠卷组
			CouponGroupRelPO couponGroupRelPO = new CouponGroupRelPO();
			couponGroupRelPO.setCouponGroupId(couponGroupId);
			List<CouponGroupRelPO> couponGroupRelPOList =  couponGroupRelReadDAO.findAll(couponGroupRelPO,null);
			boolean isNotFirstCoupon = false;
			for (CouponGroupRelPO couponGroupRelPO_ : couponGroupRelPOList) {
				List<Long> temp = getCouponCompanyByCouponId(couponGroupRelPO_.getCouponId(), officialList, competitiveList, testList);
				if (isNotFirstCoupon) {
					list.retainAll(temp);
				} else {
					list.addAll(temp);
				}
				isNotFirstCoupon = true;
			}
		} else if (couponId != null){
			
			list = getCouponCompanyByCouponId(couponId, officialList, competitiveList, testList);
		}
		
		return list;
	}
	
	private List<Long> getCouponCompanyByCouponId(Long couponId, List<Long> officialList,
			List<Long> competitiveList, List<Long> testList) {
		List<Long> list = new ArrayList<Long>();
		CouponCompanyPO couponCompanyPO = new CouponCompanyPO();
		couponCompanyPO.setCouponId(couponId);
		List<CouponCompanyPO> couponCompanyList  = couponCompanyReadDAO.findAll(couponCompanyPO,null);
		for (CouponCompanyPO couponCompanyPO_ : couponCompanyList) {
			
			Integer companyType = NormalConstant.getCompanyTypeByCompanyId(couponCompanyPO_.getCompanyId());
			if (companyType != null){
				if (companyType == NormalConstant.COMPANY_OFFICIAL) {
					
					list.addAll(officialList);
				} else if (companyType == NormalConstant.COMPANY_COMPETITIVE_PRODUCTS) {
					
					list.addAll(competitiveList);
				} else if (companyType == NormalConstant.COMPANY_TEST) {
					
					list.addAll(testList);
				}
			} else {
				
				list.add(couponCompanyPO_.getCompanyId());
			}
		}
		
		return list;
	}
	
}
	