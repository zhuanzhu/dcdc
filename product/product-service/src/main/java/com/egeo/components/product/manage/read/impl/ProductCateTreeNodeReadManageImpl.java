package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductCateTreeNodeReadManage;
import com.egeo.components.product.dao.read.ProductCateTreeNodeReadDAO;
import com.egeo.components.product.po.ProductCateTreeNodePO;

@Service
public class ProductCateTreeNodeReadManageImpl implements ProductCateTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductCateTreeNodeReadDAO productCateTreeNodeReadDAO;
	@Override
	public List<ProductCateTreeNodePO> findAll(ProductCateTreeNodePO productCateTreeNodePO) {

		return productCateTreeNodeReadDAO.findAll(productCateTreeNodePO,null);
	}
	
}
	