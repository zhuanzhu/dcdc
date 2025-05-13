package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.ActivityMerchantProdPO;

public interface ActivityMerchantProdWriteManage {

	Long saveActivityMerchantProd(ActivityMerchantProdPO po);

	Integer deleteById(ActivityMerchantProdPO po);

	Long updateActivityMerchantProd(ActivityMerchantProdPO po);
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	Integer deleteActivityMerchantByActivityId(Long activityId);
	/**
	 * 根据商品id删除活动商品关系表
	 * @param merchantProductId
	 * @return
	 */
	int deleteByMerchantProductIdWithTx(Long merchantProductId);
}
	