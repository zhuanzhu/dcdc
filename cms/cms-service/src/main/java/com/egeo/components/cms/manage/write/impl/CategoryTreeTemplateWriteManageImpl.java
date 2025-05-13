package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.dao.write.CategoryTreeTemplateWriteDAO;
import com.egeo.components.cms.manage.write.CategoryTreeTemplateWriteManage;
import com.egeo.components.cms.po.CategoryTreeTemplatePO;
import com.egeo.exception.BusinessException;

@Service
public class CategoryTreeTemplateWriteManageImpl implements CategoryTreeTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeTemplateWriteDAO categoryTreeTemplateWriteDAO;

	@Override
	public Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po) {
		
		int i ;
		try {
				i = categoryTreeTemplateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po) {
		int i;
		i = categoryTreeTemplateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po) {
		int i;
		i = categoryTreeTemplateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	