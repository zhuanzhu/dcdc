package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.DoReadManage;
import com.egeo.components.order.dao.read.DoReadDAO;

@Service
public class DoReadManageImpl implements DoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DoReadDAO doReadDAO;
	
}
	