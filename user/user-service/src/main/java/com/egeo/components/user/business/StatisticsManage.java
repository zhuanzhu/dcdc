package com.egeo.components.user.business;

import java.util.Map;

public interface StatisticsManage {
	/**
	 * 统计平台商品、订单、用户数据
	 * @param platformId
	 * @return
	 */
	public Map<String, Object> dataStatistics(Long storeId, Long platformId);
}
