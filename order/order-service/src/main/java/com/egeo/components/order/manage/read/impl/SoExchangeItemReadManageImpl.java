package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoExchangeItemReadManage;
import com.egeo.components.order.dao.read.SoExchangeItemReadDAO;

@Service
public class SoExchangeItemReadManageImpl implements SoExchangeItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoExchangeItemReadDAO soExchangeItemReadDAO;
	
}
	