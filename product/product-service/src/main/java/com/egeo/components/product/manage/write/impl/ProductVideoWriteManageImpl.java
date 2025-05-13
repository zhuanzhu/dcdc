package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductVideoWriteManage;
import com.egeo.components.product.dao.write.ProductVideoWriteDAO;

@Service
public class ProductVideoWriteManageImpl implements ProductVideoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductVideoWriteDAO productVideoWriteDAO;
}
	