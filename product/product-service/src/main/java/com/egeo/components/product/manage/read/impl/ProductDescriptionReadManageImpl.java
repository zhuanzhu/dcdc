package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductDescriptionReadManage;
import com.egeo.components.product.dao.read.ProductDescriptionReadDAO;
import com.egeo.components.product.po.ProductDescriptionPO;

@Service
public class ProductDescriptionReadManageImpl implements ProductDescriptionReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductDescriptionReadDAO productDescriptionReadDAO;
	
        @Override
        public List<ProductDescriptionPO> findAll(ProductDescriptionPO po) {
            
            return productDescriptionReadDAO.findAll(po,null);
        }

        @Override
        public ProductDescriptionPO findById(ProductDescriptionPO po) {
            
            return productDescriptionReadDAO.findById(po);
        }
	
}
	