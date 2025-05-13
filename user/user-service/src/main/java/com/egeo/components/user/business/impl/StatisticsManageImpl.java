package com.egeo.components.user.business.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.StatisticsManage;
import com.egeo.components.user.facade.StatisticsFacade;
@Service("statistics")
public class StatisticsManageImpl implements StatisticsManage {
	
	@Resource(name="statisticsFacade")
	private StatisticsFacade statisticsFacade;
	@Override
	public Map<String, Object> dataStatistics(Long storeId, Long platformId) {
		return statisticsFacade.dataStatistics(storeId, platformId);
	}

}
