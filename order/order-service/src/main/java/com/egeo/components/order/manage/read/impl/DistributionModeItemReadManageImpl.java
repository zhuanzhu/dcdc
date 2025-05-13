package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.DistributionModeItemReadManage;
import com.egeo.components.order.dao.read.DistributionModeItemReadDAO;

@Service
public class DistributionModeItemReadManageImpl implements DistributionModeItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DistributionModeItemReadDAO distributionModeItemReadDAO;
	
}
	