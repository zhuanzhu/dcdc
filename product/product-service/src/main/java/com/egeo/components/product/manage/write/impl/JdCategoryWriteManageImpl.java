package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.JdCategoryWriteManage;
import com.egeo.components.product.dao.write.JdCategoryWriteDAO;
import com.egeo.components.product.po.JdCategoryPO;
import com.egeo.exception.BusinessException;

@Service
public class JdCategoryWriteManageImpl implements JdCategoryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdCategoryWriteDAO jdCategoryWriteDAO;

	@Override
	public Long insertJdCategoryWithTx(JdCategoryPO po) {
		
		int i ;
		try {
				i = jdCategoryWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateJdCategoryWithTx(JdCategoryPO po) {
		int i;
		i = jdCategoryWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteJdCategoryWithTx(JdCategoryPO po) {
		int i;
		i = jdCategoryWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	