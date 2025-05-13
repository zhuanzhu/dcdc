package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductCauseWriteManage;
import com.egeo.components.product.dao.write.ProductCauseWriteDAO;
import com.egeo.components.product.po.ProductCausePO;
import com.egeo.exception.BusinessException;

@Service
public class ProductCauseWriteManageImpl implements ProductCauseWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductCauseWriteDAO productCauseWriteDAO;
	
        @Override
        public Long saveProductCause(ProductCausePO po) {
            productCauseWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("新增产品未通过原因失败");
            }
            
        }
}
	