package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryBannerWriteManage;
import com.egeo.components.product.dao.write.CategoryBannerWriteDAO;
import com.egeo.components.product.po.CategoryBannerPO;
import com.egeo.exception.BusinessException;

@Service
public class CategoryBannerWriteManageImpl implements CategoryBannerWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryBannerWriteDAO categoryBannerWriteDAO;

	@Override
	public Long insertCategoryBannerWithTx(CategoryBannerPO po) {
		
		int i ;
		try {
				i = categoryBannerWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCategoryBannerWithTx(CategoryBannerPO po) {
		int i;
		i = categoryBannerWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCategoryBannerWithTx(CategoryBannerPO po) {
		int i;
		i = categoryBannerWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	