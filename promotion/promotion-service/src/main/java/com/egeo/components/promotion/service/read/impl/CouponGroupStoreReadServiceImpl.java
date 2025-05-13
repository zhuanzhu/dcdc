package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponGroupStoreReadService;
import com.egeo.components.promotion.manage.read.CouponGroupStoreReadManage;
import com.egeo.components.promotion.converter.CouponGroupStoreConverter;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.po.CouponGroupStorePO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponGroupStoreReadService")
public class CouponGroupStoreReadServiceImpl implements CouponGroupStoreReadService {
	@Autowired
	private CouponGroupStoreReadManage couponGroupStoreReadManage;

	@Override
	public CouponGroupStoreDTO findCouponGroupStoreById(CouponGroupStoreDTO dto) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
		CouponGroupStorePO list = couponGroupStoreReadManage.findCouponGroupStoreById(po);		
		return CouponGroupStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponGroupStoreDTO> findCouponGroupStoreOfPage(CouponGroupStoreDTO dto, Pagination page) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
        PageResult<CouponGroupStorePO> pageResult = couponGroupStoreReadManage.findCouponGroupStoreOfPage(po, page);
        
        List<CouponGroupStoreDTO> list = CouponGroupStoreConverter.toDTO(pageResult.getList());
        PageResult<CouponGroupStoreDTO> result = new PageResult<CouponGroupStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
		List<CouponGroupStorePO> list = couponGroupStoreReadManage.findCouponGroupStoreAll(po);		
		return CouponGroupStoreConverter.toDTO(list);
	}
}
	