package com.egeo.components.promotion.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.ActivityMerchantProdPO;
import com.egeo.orm.BaseWriteDAO;

public interface ActivityMerchantProdWriteDAO extends BaseWriteDAO<ActivityMerchantProdPO> {
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	Integer deleteActivityMerchantByActivityId(@Param("activityId")Long activityId);
}
	