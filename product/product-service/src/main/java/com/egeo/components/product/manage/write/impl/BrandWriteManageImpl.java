package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.BrandWriteManage;
import com.egeo.components.product.dao.write.BrandWriteDAO;
import com.egeo.components.product.po.BrandPO;
import com.egeo.exception.BusinessException;

@Service
public class BrandWriteManageImpl implements BrandWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BrandWriteDAO brandWriteDAO;
	
        @Override
        public Long saveBrandWithTx(BrandPO po) {
            brandWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("添加品牌信息失败");
            }
            
        }

        @Override
        public String updateBrand(BrandPO po) {
            return brandWriteDAO.update(po)+"";
        }
}
	