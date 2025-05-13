package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponStoreReadService;
import com.egeo.components.promotion.manage.read.CouponStoreReadManage;
import com.egeo.components.promotion.converter.CouponStoreConverter;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.po.CouponStorePO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponStoreReadService")
public class CouponStoreReadServiceImpl implements CouponStoreReadService {
	@Autowired
	private CouponStoreReadManage couponStoreReadManage;

	@Override
	public CouponStoreDTO findCouponStoreById(CouponStoreDTO dto) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
		CouponStorePO list = couponStoreReadManage.findCouponStoreById(po);		
		return CouponStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponStoreDTO> findCouponStoreOfPage(CouponStoreDTO dto, Pagination page) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
        PageResult<CouponStorePO> pageResult = couponStoreReadManage.findCouponStoreOfPage(po, page);
        
        List<CouponStoreDTO> list = CouponStoreConverter.toDTO(pageResult.getList());
        PageResult<CouponStoreDTO> result = new PageResult<CouponStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponStoreDTO> findCouponStoreAll(CouponStoreDTO dto) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
		List<CouponStorePO> list = couponStoreReadManage.findCouponStoreAll(po);		
		return CouponStoreConverter.toDTO(list);
	}
}
	