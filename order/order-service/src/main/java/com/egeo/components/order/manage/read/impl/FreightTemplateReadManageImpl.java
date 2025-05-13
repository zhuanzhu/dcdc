package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.FreightTemplateReadManage;
import com.egeo.components.order.dao.read.FreightTemplateReadDAO;

@Service
public class FreightTemplateReadManageImpl implements FreightTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightTemplateReadDAO freightTemplateReadDAO;
	
}
	