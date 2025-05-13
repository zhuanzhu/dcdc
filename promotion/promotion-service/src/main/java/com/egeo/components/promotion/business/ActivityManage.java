package com.egeo.components.promotion.business;

import java.util.List;

import com.egeo.components.promotion.vo.ActivityMerchantProd;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.components.promotion.vo.ActivityVO;
import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityManage {

	PageResult<ActivityDTO> findPageActivity(Pagination page, ActivityVO activityVO);

	Long saveActivity(ActivityVO activityVO);

	Integer delete(ActivityVO activityVO);

	Long updateActivity(ActivityVO activityVO);

	ActivityVO findById(ActivityVO activityVO);

	String saveActivityMerchantProd(List<ActivityMerchantProdVO> lists);

	PageResult<ActivityMerchantProd> PageActivityMerchantProd(Pagination page, ActivityVO activityVO);

	List<ActivityVO> findAll(ActivityVO activityVO);
	/**
	 * 查询指定第几个活动
	 * @param pages
	 * @return
	 */
	ActivityMerchantProd activityMerchantProdByPages(Integer pages,Long platformId);
	

}
	