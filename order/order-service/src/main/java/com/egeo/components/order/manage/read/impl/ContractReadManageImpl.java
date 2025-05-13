package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.ContractReadManage;
import com.egeo.components.order.dao.read.ContractReadDAO;

@Service
public class ContractReadManageImpl implements ContractReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContractReadDAO contractReadDAO;
	
}
	