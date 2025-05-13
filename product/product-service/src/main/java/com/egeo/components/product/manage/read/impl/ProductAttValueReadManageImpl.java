package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductAttValueReadManage;
import com.egeo.components.product.dao.read.ProductAttValueReadDAO;
import com.egeo.components.product.po.ProductAttValuePO;

@Service
public class ProductAttValueReadManageImpl implements ProductAttValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductAttValueReadDAO productAttValueReadDAO;
	
        @Override
        public List<ProductAttValuePO> findAll(ProductAttValuePO po) {
            
            return productAttValueReadDAO.findAll(po,null);
        }
	
}
	