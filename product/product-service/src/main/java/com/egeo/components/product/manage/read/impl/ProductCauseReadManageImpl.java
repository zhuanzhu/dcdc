package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductCauseReadManage;
import com.egeo.components.product.dao.read.ProductCauseReadDAO;
import com.egeo.components.product.po.ProductCausePO;

@Service
public class ProductCauseReadManageImpl implements ProductCauseReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductCauseReadDAO productCauseReadDAO;
	
        @Override
        public List<ProductCausePO> findByProductId(Long productId) {
            
            return productCauseReadDAO.findByProductId(productId);
        }
	
}
	