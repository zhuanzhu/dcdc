package com.egeo.components.pay.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.PayAliLogReadDAO;
import com.egeo.components.pay.manage.read.PayAliLogReadManage;


@Service
public class PayAliLogReadManageImpl implements PayAliLogReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayAliLogReadDAO payAliLogReadDAO;
	


	
}
	