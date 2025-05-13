package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoProcessConfigWriteManage;
import com.egeo.components.order.dao.write.SoProcessConfigWriteDAO;

@Service
public class SoProcessConfigWriteManageImpl implements SoProcessConfigWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoProcessConfigWriteDAO soProcessConfigWriteDAO;
}
	