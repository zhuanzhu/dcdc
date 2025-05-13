package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.CouponGroupStoreReadService;
import com.egeo.components.promotion.service.write.CouponGroupStoreWriteService;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CouponGroupStoreFacade {
	
	@Autowired
	private CouponGroupStoreReadService couponGroupStoreReadService;
	
	@Autowired
	private CouponGroupStoreWriteService couponGroupStoreWriteService;
	
	
	public CouponGroupStoreDTO findCouponGroupStoreById(CouponGroupStoreDTO dto){
		
		return couponGroupStoreReadService.findCouponGroupStoreById(dto);
	}

	public PageResult<CouponGroupStoreDTO> findCouponGroupStoreOfPage(CouponGroupStoreDTO dto,Pagination page){
		
		return couponGroupStoreReadService.findCouponGroupStoreOfPage(dto, page);
		
	}

	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto){
		
		return couponGroupStoreReadService.findCouponGroupStoreAll(dto);
		
	}

	public Long insertCouponGroupStoreWithTx(CouponGroupStoreDTO dto){
		
		return couponGroupStoreWriteService.insertCouponGroupStoreWithTx(dto);
	}

	public int updateCouponGroupStoreWithTx(CouponGroupStoreDTO dto){
		
		return couponGroupStoreWriteService.updateCouponGroupStoreWithTx(dto);
	}

	public int deleteCouponGroupStoreWithTx(CouponGroupStoreDTO dto){
		
		return couponGroupStoreWriteService.deleteCouponGroupStoreWithTx(dto);
		
	}

}
	