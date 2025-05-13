package com.egeo.components.promotion.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.condition.ActivityMerchantProdCondition;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;
import com.egeo.orm.BaseReadDAO;

public interface ActivityMerchantProdReadDAO extends BaseReadDAO<ActivityMerchantProdPO>{
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	List<ActivityMerchantProdCondition> activityMerchantProdByPages(@Param("date")Date date, @Param("pages")Integer pages,@Param("platformId")Long platformId);
}
	