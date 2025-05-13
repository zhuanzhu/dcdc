package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoExchangeReadManage;
import com.egeo.components.order.dao.read.SoExchangeReadDAO;

@Service
public class SoExchangeReadManageImpl implements SoExchangeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoExchangeReadDAO soExchangeReadDAO;
	
}
	