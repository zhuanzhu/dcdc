package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.FreightTemplateWriteManage;
import com.egeo.components.order.dao.write.FreightTemplateWriteDAO;

@Service
public class FreightTemplateWriteManageImpl implements FreightTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightTemplateWriteDAO freightTemplateWriteDAO;
}
	