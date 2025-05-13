package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductDescriptionWriteManage;
import com.egeo.components.product.dao.write.ProductDescriptionWriteDAO;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ProductDescriptionWriteManageImpl implements ProductDescriptionWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductDescriptionWriteDAO productDescriptionWriteDAO;
	
        @Override
        public Long saveProductDescription(ProductDescriptionPO po) {
            productDescriptionWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("新增产品描述信息失败");
            }
            
        }

        @Override
        public String updateProductDescriptionWithTx(ProductDescriptionPO productDescriptionPO2) {
            
            return productDescriptionWriteDAO.update(productDescriptionPO2)+"";
        }

        @Override
        public String deleteByProductId(Long productId) {
            
            return productDescriptionWriteDAO.deleteByProductId(productId)+"";
        }

    @Override
    public void saveProductDescriptionList(List<ProductDescriptionPO> productDescriptionPOList) {
            try{
        productDescriptionWriteDAO.saveProductDescriptionList(productDescriptionPOList);
    }catch (Exception e){
        logger.error("saveProductDescriptionList失败,e:"+e.getMessage());
    }
    }

    @Override
    public void updateProductDescriptionSWithTx(ProductDescriptionPO productDescriptionPO) {
      productDescriptionWriteDAO.updateProductDescriptionSWithTx(productDescriptionPO);
    }
}
	