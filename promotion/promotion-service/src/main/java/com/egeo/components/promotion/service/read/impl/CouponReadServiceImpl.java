package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponReadService;
import com.egeo.components.promotion.manage.read.CouponReadManage;
import com.egeo.components.promotion.converter.CouponConverter;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.po.CouponPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("couponReadService")
public class CouponReadServiceImpl implements CouponReadService {
	@Autowired
	private CouponReadManage couponReadManage;

	@Override
	public CouponDTO findCouponById(CouponDTO dto) {
		CouponPO po = CouponConverter.toPO(dto);
		CouponPO list = couponReadManage.findCouponById(po);		
		return CouponConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponDTO> findCouponOfPage(CouponDTO dto, Pagination page) {
		CouponPO po = CouponConverter.toPO(dto);
        PageResult<CouponPO> pageResult = couponReadManage.findCouponOfPage(po, page);
        
        List<CouponDTO> list = CouponConverter.toDTO(pageResult.getList());
        PageResult<CouponDTO> result = new PageResult<CouponDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponDTO> findCouponAll(CouponDTO dto) {
		CouponPO po = CouponConverter.toPO(dto);
		List<CouponPO> list = couponReadManage.findCouponAll(po);		
		return CouponConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponDTO> findCouponOfPageByBlurry(CouponDTO dto, List<Long> couponIdList, Pagination page) {
		CouponPO po = CouponConverter.toPO(dto);
		if (EmptyUtil.isEmpty(couponIdList))
			couponIdList = null;
        PageResult<CouponPO> pageResult = couponReadManage.findCouponOfPageByBlurry(po, couponIdList, page);
        
        List<CouponDTO> list = CouponConverter.toDTO(pageResult.getList());
        PageResult<CouponDTO> result = new PageResult<CouponDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	public List<CouponDTO> findCouponByCouponGroupId(Long couponRelId) {
		return CouponConverter.toDTO(couponReadManage.findCouponByCouponGroupId(couponRelId));
	}
}
	