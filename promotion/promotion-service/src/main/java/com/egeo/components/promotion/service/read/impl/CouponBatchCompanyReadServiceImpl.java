package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponBatchCompanyReadService;
import com.egeo.components.promotion.manage.read.CouponBatchCompanyReadManage;
import com.egeo.components.promotion.converter.CouponBatchCompanyConverter;
import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponBatchCompanyReadService")
public class CouponBatchCompanyReadServiceImpl implements CouponBatchCompanyReadService {
	@Autowired
	private CouponBatchCompanyReadManage couponBatchCompanyReadManage;

	@Override
	public CouponBatchCompanyDTO findCouponBatchCompanyById(CouponBatchCompanyDTO dto) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
		CouponBatchCompanyPO list = couponBatchCompanyReadManage.findCouponBatchCompanyById(po);		
		return CouponBatchCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponBatchCompanyDTO> findCouponBatchCompanyOfPage(CouponBatchCompanyDTO dto, Pagination page) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
        PageResult<CouponBatchCompanyPO> pageResult = couponBatchCompanyReadManage.findCouponBatchCompanyOfPage(po, page);
        
        List<CouponBatchCompanyDTO> list = CouponBatchCompanyConverter.toDTO(pageResult.getList());
        PageResult<CouponBatchCompanyDTO> result = new PageResult<CouponBatchCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponBatchCompanyDTO> findCouponBatchCompanyAll(CouponBatchCompanyDTO dto) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
		List<CouponBatchCompanyPO> list = couponBatchCompanyReadManage.findCouponBatchCompanyAll(po);		
		return CouponBatchCompanyConverter.toDTO(list);
	}
}
	