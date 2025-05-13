package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.ExpressMapConfigWriteManage;
import com.egeo.components.order.dao.write.ExpressMapConfigWriteDAO;

@Service
public class ExpressMapConfigWriteManageImpl implements ExpressMapConfigWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExpressMapConfigWriteDAO expressMapConfigWriteDAO;
}
	