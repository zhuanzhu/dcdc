package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoMessageRelWriteManage;
import com.egeo.components.order.dao.write.SoMessageRelWriteDAO;

@Service
public class SoMessageRelWriteManageImpl implements SoMessageRelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoMessageRelWriteDAO soMessageRelWriteDAO;
}
	