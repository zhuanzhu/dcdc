package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExchangeCouponUnitStatusReadManage {

	public ExchangeCouponUnitStatusPO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusPO po);

	public PageResult<ExchangeCouponUnitStatusPO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusPO po,Pagination page);

	public List<ExchangeCouponUnitStatusPO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusPO po);

	public List<Integer> findUnitStatusAll(ExchangeCouponUnitStatusPO po);
}
	