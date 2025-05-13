package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponGroupStoreManage {

	public CouponGroupStoreDTO findCouponGroupStoreById(CouponGroupStoreDTO dto);	

	public PageResult<CouponGroupStoreDTO> findCouponGroupStoreOfPage(CouponGroupStoreDTO dto,Pagination page);

	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto);

	Long insertCouponGroupStoreWithTx(CouponGroupStoreDTO dto);

	int updateCouponGroupStoreWithTx(CouponGroupStoreDTO dto);

	int deleteCouponGroupStoreWithTx(CouponGroupStoreDTO dto);
}
	