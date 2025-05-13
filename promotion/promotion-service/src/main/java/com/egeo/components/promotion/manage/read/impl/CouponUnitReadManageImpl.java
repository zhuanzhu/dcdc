package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CouponUnitReadManage;
import com.egeo.components.promotion.condition.CouponUnitCondition;
import com.egeo.components.promotion.dao.read.CouponUnitReadDAO;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CouponUnitReadManageImpl implements CouponUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponUnitReadDAO couponUnitReadDAO;
	
	public CouponUnitPO findCouponUnitById(CouponUnitPO po) {
		CouponUnitPO couponUnitpo = new CouponUnitPO();
		couponUnitpo.setId(po.getId());
		return couponUnitReadDAO.findById(couponUnitpo);
	}

	public PageResult<CouponUnitPO> findCouponUnitOfPage(CouponUnitPO po, Pagination page) {
		
		PageResult<CouponUnitPO> pageResult = new PageResult<CouponUnitPO>();
		List<CouponUnitPO> list = null;

		int cnt = couponUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CouponUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CouponUnitPO> findCouponUnitAll(CouponUnitPO po) {

		return couponUnitReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<CouponUnitCondition> findCouponUnitOfPageByBlurry(CouponUnitCondition po, List<Long> userList,
			Pagination page) {
		PageResult<CouponUnitCondition> pageResult = new PageResult<CouponUnitCondition>();
		List<CouponUnitCondition> list = null;

		int cnt = couponUnitReadDAO.countOfPageByBlurry(po, userList);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponUnitReadDAO.findOfPageByBlurry(po, userList, page);
		} else {
			list = new ArrayList<CouponUnitCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public PageResult<CouponUnitCondition> findCouponUnitOfPageByUser(CouponUnitCondition po, Pagination page) {
		PageResult<CouponUnitCondition> pageResult = new PageResult<CouponUnitCondition>();
		List<CouponUnitCondition> list = null;

		int cnt = couponUnitReadDAO.countOfPageByUser(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponUnitReadDAO.findOfPageByUser(po, page);
		} else {
			list = new ArrayList<CouponUnitCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public PageResult<CouponUnitCondition> findCouponUnitCenterOfPage(CouponUnitCondition po, Long companyIdByType, Pagination page) {
		page.setOrderBy("cb.effect_start_time,cb.effect_end_time,cb.create_time");
		PageResult<CouponUnitCondition> pageResult = new PageResult<CouponUnitCondition>();
		List<CouponUnitCondition> list = null;

		int cnt = couponUnitReadDAO.countCouponUnitCenterOfPage(po, companyIdByType);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = couponUnitReadDAO.findCouponUnitCenterOfPage(po, companyIdByType, page);
		} else {
			list = new ArrayList<CouponUnitCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<CouponUnitCondition> findSUCouponBatchOfPage(CouponUnitCondition po,
			List<Long> goodIdList, Long companyIdByType, Pagination page) {
		
		List<CouponUnitCondition> list = couponUnitReadDAO.findSUCouponBatchOfPage(po, goodIdList, companyIdByType, page);

		return list;
	}

	@Override
	public List<CouponUnitCondition> findCouponUnitOfAllByUser(CouponUnitCondition po) {
		
		return couponUnitReadDAO.findCouponUnitOfAllByUser(po);
	}

	@Override
	public List<CouponUnitCondition> findCouponUnitListByBatchIdList(List<Long> couponBatchList) {
		return couponUnitReadDAO.findCouponUnitListByBatchIdList(couponBatchList);
	}

	@Override
	public Long findCouponUnitAllCount(CouponUnitPO couponUnitPO) {
		return couponUnitReadDAO.findCouponUnitAllCount(couponUnitPO);
	}

	@Override
	public List<CouponUnitCondition> findCouponUnitAndBatchExchange(CouponUnitCondition po, Long companyIdByType) {

		return couponUnitReadDAO.findCouponUnitAndBatchExchange(po, companyIdByType);

	}

	@Override
	public Long findCouponUnitCountOfFreezeByParams(Long couponBatchId, Long startNum, Long endNum) {
		return couponUnitReadDAO.findCouponUnitCountOfFreezeByParams(couponBatchId,startNum,endNum);
	}

	@Override
	public Integer countUnreadCouponUnit(Long userId, Integer couponType) {
		// TODO Auto-generated method stub
		return couponUnitReadDAO.countUnreadCouponUnit(userId, couponType);
	}

}
	