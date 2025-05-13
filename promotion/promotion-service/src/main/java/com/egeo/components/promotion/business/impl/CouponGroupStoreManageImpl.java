package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.CouponGroupStoreManage;
import com.egeo.components.promotion.facade.CouponGroupStoreFacade;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponGroupStore")
public class CouponGroupStoreManageImpl implements CouponGroupStoreManage{

	
	@Resource(name="couponGroupStoreFacade")
	private CouponGroupStoreFacade couponGroupStoreFacade;

	@Override
	public CouponGroupStoreDTO findCouponGroupStoreById(CouponGroupStoreDTO dto) {
		return couponGroupStoreFacade.findCouponGroupStoreById(dto);
	}

	@Override
	public PageResult<CouponGroupStoreDTO> findCouponGroupStoreOfPage(CouponGroupStoreDTO dto, Pagination page) {
		return couponGroupStoreFacade.findCouponGroupStoreOfPage(dto, page);
	}

	@Override
	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto) {
		return couponGroupStoreFacade.findCouponGroupStoreAll(dto);
	}

	@Override
	public Long insertCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		return couponGroupStoreFacade.insertCouponGroupStoreWithTx(dto);
	}

	@Override
	public int updateCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		return couponGroupStoreFacade.updateCouponGroupStoreWithTx(dto);
	}

	@Override
	public int deleteCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		return couponGroupStoreFacade.deleteCouponGroupStoreWithTx(dto);
	}


}
	