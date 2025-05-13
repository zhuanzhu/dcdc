package com.egeo.components.promotion.facade;

import java.util.List;

import com.egeo.components.promotion.service.read.CouponGroupStoreReadService;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.CouponGroupReadService;
import com.egeo.components.promotion.service.read.CouponGroupRelReadService;
import com.egeo.components.promotion.service.write.CouponGroupWriteService;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CouponGroupFacade {
	
	@Autowired
	private CouponGroupReadService couponGroupReadService;
	
	@Autowired
	private CouponGroupWriteService couponGroupWriteService;
	
	@Autowired
	private CouponGroupRelReadService couponGroupRelReadService;

	@Autowired
	private CouponGroupStoreReadService couponGroupStoreReadService;

	public CouponGroupDTO findCouponGroupById(CouponGroupDTO dto){
		return couponGroupReadService.findCouponGroupById(dto);
	}

	public PageResult<CouponGroupDTO> findCouponGroupOfPage(CouponGroupDTO dto,Pagination page){
		return couponGroupReadService.findCouponGroupOfPage(dto, page);
	}

	public List<CouponGroupDTO> findCouponGroupAll(CouponGroupDTO dto){
		return couponGroupReadService.findCouponGroupAll(dto);
	}

	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto){
		return couponGroupStoreReadService.findCouponGroupStoreAll(dto);
	}

	public Long insertCouponGroupWithTx(CouponGroupDTO dto){
		
		return couponGroupWriteService.insertCouponGroupWithTx(dto);
	}

	public int updateCouponGroupWithTx(CouponGroupDTO dto){
		
		return couponGroupWriteService.updateCouponGroupWithTx(dto);
	}

	public int deleteCouponGroupWithTx(CouponGroupDTO dto){
		
		return couponGroupWriteService.deleteCouponGroupWithTx(dto);
		
	}

	/**
	 * 查询优惠卷列表(模糊查询)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponGroupDTO> findCouponGroupOfPageByBlurry(CouponGroupDTO dto, Pagination page) {
		
		return couponGroupReadService.findCouponGroupOfPageByBlurry(dto, page);
	}

	/**
	 * 查询优惠卷分组的优惠卷信息
	 * @param id
	 * @return
	 */
	public List<CouponGroupRelDTO> queryCouponGroupRelByCouponId(Long id) {
		CouponGroupRelDTO dto =new CouponGroupRelDTO();
		dto.setCouponGroupId(id);
		return couponGroupRelReadService.findCouponGroupRelAll(dto);
	}

    public List<Long> findCouponIdListByGroupId(Long couponRelId) {
		return couponGroupRelReadService.findCouponIdListByGroupId(couponRelId);
    }
}
	