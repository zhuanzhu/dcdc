package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTagWriteManage;
import com.egeo.components.product.dao.write.CategoryTagWriteDAO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.exception.BusinessException;

@Service
public class CategoryTagWriteManageImpl implements CategoryTagWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTagWriteDAO categoryTagWriteDAO;

	@Override
	public Long insertCategoryTagWithTx(CategoryTagPO po) {
		
		int i ;
		try {
				i = categoryTagWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCategoryTagWithTx(CategoryTagPO po) {
		int i;
		i = categoryTagWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCategoryTagWithTx(CategoryTagPO po) {
		int i;
		i = categoryTagWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	