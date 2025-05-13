package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductAttNameWriteManage;
import com.egeo.components.product.dao.write.ProductAttNameWriteDAO;
import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ProductAttNameWriteManageImpl implements ProductAttNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductAttNameWriteDAO productAttNameWriteDAO;
	
        @Override
        public Long saveProductAttNameWithTx(ProductAttNamePO po) {
            productAttNameWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("新增产品属性失败");
            }
            
        }

        @Override
        public String deleteByProductIdWithTx(Long productId) {
            
            return productAttNameWriteDAO.deleteByProductId(productId)+"";
        }

    @Override
    public void saveProductAttName(List<ProductAttNamePO> productAttNamePOList) {
            try{
                productAttNameWriteDAO.saveProductAttName( productAttNamePOList);
            }catch (Exception e){
                logger.error("saveProductAttName失败,e:"+e.getMessage());
            }

    }
}
	