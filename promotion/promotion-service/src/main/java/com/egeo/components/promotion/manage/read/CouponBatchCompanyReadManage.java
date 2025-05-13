package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponBatchCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponBatchCompanyReadManage {

	public CouponBatchCompanyPO findCouponBatchCompanyById(CouponBatchCompanyPO po);

	public PageResult<CouponBatchCompanyPO> findCouponBatchCompanyOfPage(CouponBatchCompanyPO po,Pagination page);

	public List<CouponBatchCompanyPO> findCouponBatchCompanyAll(CouponBatchCompanyPO po);
}
	