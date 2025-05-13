package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.DoItemWriteManage;
import com.egeo.components.order.dao.write.DoItemWriteDAO;

@Service
public class DoItemWriteManageImpl implements DoItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DoItemWriteDAO doItemWriteDAO;
}
	