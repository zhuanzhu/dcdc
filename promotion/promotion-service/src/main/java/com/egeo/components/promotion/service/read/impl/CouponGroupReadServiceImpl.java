package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponGroupReadService;
import com.egeo.components.promotion.manage.read.CouponGroupReadManage;
import com.egeo.components.promotion.converter.CouponGroupConverter;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.po.CouponGroupPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponGroupReadService")
public class CouponGroupReadServiceImpl implements CouponGroupReadService {
	@Autowired
	private CouponGroupReadManage couponGroupReadManage;

	@Override
	public CouponGroupDTO findCouponGroupById(CouponGroupDTO dto) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
		CouponGroupPO list = couponGroupReadManage.findCouponGroupById(po);		
		return CouponGroupConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponGroupDTO> findCouponGroupOfPage(CouponGroupDTO dto, Pagination page) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
        PageResult<CouponGroupPO> pageResult = couponGroupReadManage.findCouponGroupOfPage(po, page);
        
        List<CouponGroupDTO> list = CouponGroupConverter.toDTO(pageResult.getList());
        PageResult<CouponGroupDTO> result = new PageResult<CouponGroupDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponGroupDTO> findCouponGroupAll(CouponGroupDTO dto) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
		List<CouponGroupPO> list = couponGroupReadManage.findCouponGroupAll(po);		
		return CouponGroupConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponGroupDTO> findCouponGroupOfPageByBlurry(CouponGroupDTO dto, Pagination page) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
        PageResult<CouponGroupPO> pageResult = couponGroupReadManage.findCouponGroupOfPageByBlurry(po, page);
        
        List<CouponGroupDTO> list = CouponGroupConverter.toDTO(pageResult.getList());
        PageResult<CouponGroupDTO> result = new PageResult<CouponGroupDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	