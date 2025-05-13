package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponCompanyReadService;
import com.egeo.components.promotion.manage.read.CouponCompanyReadManage;
import com.egeo.components.promotion.converter.CouponCompanyConverter;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.po.CouponCompanyPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponCompanyReadService")
public class CouponCompanyReadServiceImpl implements CouponCompanyReadService {
	@Autowired
	private CouponCompanyReadManage couponCompanyReadManage;

	@Override
	public CouponCompanyDTO findCouponCompanyById(CouponCompanyDTO dto) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
		CouponCompanyPO list = couponCompanyReadManage.findCouponCompanyById(po);		
		return CouponCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponCompanyDTO> findCouponCompanyOfPage(CouponCompanyDTO dto, Pagination page) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
        PageResult<CouponCompanyPO> pageResult = couponCompanyReadManage.findCouponCompanyOfPage(po, page);
        
        List<CouponCompanyDTO> list = CouponCompanyConverter.toDTO(pageResult.getList());
        PageResult<CouponCompanyDTO> result = new PageResult<CouponCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CouponCompanyDTO> findCouponCompanyAll(CouponCompanyDTO dto) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
		List<CouponCompanyPO> list = couponCompanyReadManage.findCouponCompanyAll(po);		
		return CouponCompanyConverter.toDTO(list);
	}

	@Override
	public List<Long> findCompanyByCouponIdOrCouponGroupId(Long couponId, Long couponGroupId, List<Long> officialList,
			List<Long> competitiveList, List<Long> testList) {
		
		return couponCompanyReadManage.findCompanyByCouponIdOrCouponGroupId(couponId, couponGroupId, officialList,
				competitiveList, testList);
	}
}
	