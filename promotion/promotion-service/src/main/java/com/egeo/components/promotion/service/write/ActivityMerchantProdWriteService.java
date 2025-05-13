package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;

public interface ActivityMerchantProdWriteService {

	Long saveActivityMerchantProdWithTx(ActivityMerchantProdDTO dto);

	Integer deleteByIdWithTx(ActivityMerchantProdDTO dto);

	Long updateActivityMerchantProdWithTx(ActivityMerchantProdDTO dto);
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	Integer deleteActivityMerchantByActivityIdWithTx(Long activityId);
	/**
	 * 根据商品id删除活动商品关系表
	 * @param merchantProductId
	 * @return
	 */
	int deleteByMerchantProductIdWithTx(Long merchantProductId);
}
	