package com.egeo.components.promotion.service.read;

import java.util.List;

import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityReadService {

	PageResult<ActivityDTO> findPageActivity(Pagination page, ActivityDTO dto);

	ActivityDTO findById(ActivityDTO dto);

	List<ActivityDTO> findAll(ActivityDTO dto);
	/**
	 * 根据商品id查询该商品对应的活动是否过期
	 * @param merchantProdId
	 * @return
	 */
	boolean activityByMerchantProdIdAndDate(Long merchantProdId);
}
	