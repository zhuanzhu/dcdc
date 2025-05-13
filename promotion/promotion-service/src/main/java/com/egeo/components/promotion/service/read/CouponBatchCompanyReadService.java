package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponBatchCompanyReadService {

	public CouponBatchCompanyDTO findCouponBatchCompanyById(CouponBatchCompanyDTO dto);

	public PageResult<CouponBatchCompanyDTO> findCouponBatchCompanyOfPage(CouponBatchCompanyDTO dto,Pagination page);

	public List<CouponBatchCompanyDTO> findCouponBatchCompanyAll(CouponBatchCompanyDTO dto);
}
	