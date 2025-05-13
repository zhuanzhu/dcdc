package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponGroupRelReadService;
import com.egeo.components.promotion.manage.read.CouponGroupRelReadManage;
import com.egeo.components.promotion.converter.CouponGroupRelConverter;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.po.CouponGroupRelPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponGroupRelReadService")
public class CouponGroupRelReadServiceImpl implements CouponGroupRelReadService {
	@Autowired
	private CouponGroupRelReadManage couponGroupRelReadManage;

	@Override
	public CouponGroupRelDTO findCouponGroupRelById(CouponGroupRelDTO dto) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
		CouponGroupRelPO list = couponGroupRelReadManage.findCouponGroupRelById(po);		
		return CouponGroupRelConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponGroupRelDTO> findCouponGroupRelOfPage(CouponGroupRelDTO dto, Pagination page) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
        PageResult<CouponGroupRelPO> pageResult = couponGroupRelReadManage.findCouponGroupRelOfPage(po, page);
        
        List<CouponGroupRelDTO> list = CouponGroupRelConverter.toDTO(pageResult.getList());
        PageResult<CouponGroupRelDTO> result = new PageResult<CouponGroupRelDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponGroupRelDTO> findCouponGroupRelAll(CouponGroupRelDTO dto) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
		List<CouponGroupRelPO> list = couponGroupRelReadManage.findCouponGroupRelAll(po);		
		return CouponGroupRelConverter.toDTO(list);
	}

	@Override
	public List<Long> findCouponIdListByGroupId(Long couponGroupId) {
		return couponGroupRelReadManage.findCouponIdListByGroupId(couponGroupId);
	}
}
	