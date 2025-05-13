package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.LeadingEndCategoryWriteManage;
import com.egeo.components.product.dao.write.LeadingEndCategoryWriteDAO;
import com.egeo.components.product.po.LeadingEndCategoryPO;
import com.egeo.exception.BusinessException;

@Service
public class LeadingEndCategoryWriteManageImpl implements LeadingEndCategoryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LeadingEndCategoryWriteDAO leadingEndCategoryWriteDAO;

	@Override
	public Long insertLeadingEndCategoryWithTx(LeadingEndCategoryPO po) {
		
		int i ;
		try {
				i = leadingEndCategoryWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLeadingEndCategoryWithTx(LeadingEndCategoryPO po) {
		int i;
		i = leadingEndCategoryWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLeadingEndCategoryWithTx(LeadingEndCategoryPO po) {
		int i;
		i = leadingEndCategoryWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	