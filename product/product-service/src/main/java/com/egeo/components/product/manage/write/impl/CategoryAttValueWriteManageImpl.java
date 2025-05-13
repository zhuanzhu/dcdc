package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryAttValueWriteManage;
import com.egeo.components.product.dao.write.CategoryAttValueWriteDAO;
import com.egeo.components.product.po.CategoryAttValuePO;
import com.egeo.exception.BusinessException;

@Service
public class CategoryAttValueWriteManageImpl implements CategoryAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryAttValueWriteDAO categoryAttValueWriteDAO;
	
        @Override
        public Long saveCategoryAttValue(CategoryAttValuePO po) {
            categoryAttValueWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("添加类目信息失败");
            }
            
        }
}
	