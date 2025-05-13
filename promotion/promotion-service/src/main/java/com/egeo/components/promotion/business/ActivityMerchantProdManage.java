package com.egeo.components.promotion.business;

import java.util.List;

import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityMerchantProdManage {

	PageResult<ActivityMerchantProdDTO> findPageActivityMerchantProd(Pagination page,
			ActivityMerchantProdVO activityMerchantProdVO);

	List<ActivityMerchantProdDTO> findAll(ActivityMerchantProdVO activityMerchantProdVO);

	PageResult<MerchantProductDTO> activityMerchantProdByMerchantProdName(Pagination page,
			ActivityMerchantProdVO activityMerchantProdVO);

	String saveActivityMerchantProd(ActivityMerchantProdVO activityMerchantProdVO,String list);

	Integer deleteById(ActivityMerchantProdVO activityMerchantProdVO);

	String updateActivityMerchantProd(List<ActivityMerchantProdVO> activityMerchantProdList);
	

}
	