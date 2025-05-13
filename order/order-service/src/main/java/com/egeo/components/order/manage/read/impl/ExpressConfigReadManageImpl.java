package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.ExpressConfigReadManage;
import com.egeo.components.order.dao.read.ExpressConfigReadDAO;

@Service
public class ExpressConfigReadManageImpl implements ExpressConfigReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExpressConfigReadDAO expressConfigReadDAO;
	
}
	