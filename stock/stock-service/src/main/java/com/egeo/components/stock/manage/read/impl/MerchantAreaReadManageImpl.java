package com.egeo.components.stock.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.MerchantAreaReadManage;
import com.egeo.components.stock.dao.read.MerchantAreaReadDAO;

@Service
public class MerchantAreaReadManageImpl implements MerchantAreaReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantAreaReadDAO merchantAreaReadDAO;
	
}
	