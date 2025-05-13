package com.egeo.components.promotion.service.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityMerchantProdReadService {

	PageResult<ActivityMerchantProdDTO> findPageActivityMerchantProd(Pagination page, ActivityMerchantProdDTO dto);

	List<ActivityMerchantProdDTO> findAll(ActivityMerchantProdDTO dto);
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	List<ActivityMerchantProdDTO> activityMerchantProdByPages(Date date, Integer pages,Long platformId);
}
	