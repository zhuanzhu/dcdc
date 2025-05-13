package com.egeo.components.order.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.RefundmentReadManage;
import com.egeo.components.order.dao.read.RefundmentReadDAO;

@Service
public class RefundmentReadManageImpl implements RefundmentReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RefundmentReadDAO refundmentReadDAO;
	
}
	