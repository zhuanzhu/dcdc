package com.egeo.components.product.manage.read.impl;

import java.util.List;

import com.egeo.components.product.po.StandardProductUnitPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductAttNameReadManage;
import com.egeo.components.product.dao.read.ProductAttNameReadDAO;
import com.egeo.components.product.po.ProductAttNamePO;

@Service
public class ProductAttNameReadManageImpl implements ProductAttNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductAttNameReadDAO productAttNameReadDAO;
	
        @Override
        public List<ProductAttNamePO> findAll(ProductAttNamePO po) {
            
            return productAttNameReadDAO.findAll(po,null);
        }

    @Override
    public ProductAttNamePO queryIsElectronicBySpuId(StandardProductUnitPO po) {

        return productAttNameReadDAO.queryIsElectronicBySpuId(po);
    }

    @Override
    public ProductAttNamePO queryIsUnitBySpuId(StandardProductUnitPO po) {
        return productAttNameReadDAO.queryIsUnitBySpuId(po);
    }

    @Override
    public Long findLastId() {
        return productAttNameReadDAO.findLastId();
    }

}
	