package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponBatchReadManage;
import com.egeo.components.promotion.condition.CouponBatchCondition;
import com.egeo.components.promotion.dao.read.CouponBatchReadDAO;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponBatchReadManageImpl implements CouponBatchReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponBatchReadDAO couponBatchReadDAO;
	
	public CouponBatchPO findCouponBatchById(CouponBatchPO po) {
		CouponBatchPO couponBatchpo = new CouponBatchPO();
		couponBatchpo.setId(po.getId());
		return couponBatchReadDAO.findById(couponBatchpo);
	}
	@Override
	public PageResult<CouponBatchCondition> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page) {
		PageResult<CouponBatchCondition> pageResult = new PageResult<CouponBatchCondition>();
		List<CouponBatchCondition> list = null;
		int cnt = couponBatchReadDAO.findCouponBatchCountByParam(batchIdList,title,couponBatchCode,couponBatchName,type,platformId);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponBatchReadDAO.findCouponBatchByParam(batchIdList,title,couponBatchCode,couponBatchName,type,platformId, page);
		} else {
			list = new ArrayList<CouponBatchCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<CouponBatchCondition> findCouponBatchByExchange(Long exchangeId) {
		return couponBatchReadDAO.findCouponBatchByExchange(exchangeId);
	}

	public PageResult<CouponBatchPO> findCouponBatchOfPage(CouponBatchPO po, Pagination page) {
		
		PageResult<CouponBatchPO> pageResult = new PageResult<CouponBatchPO>();
		List<CouponBatchPO> list = null;
		int cnt = couponBatchReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponBatchReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponBatchPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponBatchPO> findCouponBatchAll(CouponBatchPO po) {

		return couponBatchReadDAO.findAll(po,null,null);
	}

	@Override
	public PageResult<CouponBatchCondition> findCouponBatchOfPageByBlurry(CouponBatchPO po, String title,
			Pagination page) {
		// TODO Auto-generated method stub查询优惠卷批次列表
		PageResult<CouponBatchCondition> pageResult = new PageResult<CouponBatchCondition>();
		List<CouponBatchCondition> list = null;

		int cnt = couponBatchReadDAO.countOfPageByBlurry(po, title);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponBatchReadDAO.findOfPageByBlurry(po, title, page);
		} else {
			list = new ArrayList<CouponBatchCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}


	@Override
	public List<CouponBatchPO> findCouponBatchByCouponIds(CouponBatchPO po, List<Long> couponIdList, Map<String, Object> key) {
		return couponBatchReadDAO.findCouponBatchByCouponIds(po, couponIdList, key);
	}
	
	@Override
	public List<CouponBatchCondition> findRegisterCouponBatchByCouponId(List<Long> couponIdList, Long platformId) {
		return couponBatchReadDAO.findRegisterCouponBatchByCouponId(couponIdList, platformId);
	}
	
	@Override
	public List<CouponBatchCondition> findRegisterCouponBatchByCouponGroup(List<Long> couponIdList, Long platformId) {
		return couponBatchReadDAO.findRegisterCouponBatchByCouponGroup(couponIdList, platformId);
	}

	@Override
	public Long findCouponBatchCount(CouponBatchPO couponBatchPO) {
		return couponBatchReadDAO.findCouponBatchCount(couponBatchPO);
	}



}
	