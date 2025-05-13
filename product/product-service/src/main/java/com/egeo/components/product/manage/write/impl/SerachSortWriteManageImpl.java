package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SerachSortWriteManage;
import com.egeo.components.product.dao.write.SerachSortWriteDAO;
import com.egeo.components.product.po.SerachSortPO;
import com.egeo.exception.BusinessException;

@Service
public class SerachSortWriteManageImpl implements SerachSortWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SerachSortWriteDAO serachSortWriteDAO;

	@Override
	public Long insertSerachSortWithTx(SerachSortPO po) {
		
		int i ;
		try {
				i = serachSortWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSerachSortWithTx(SerachSortPO po) {
		int i;
		i = serachSortWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSerachSortWithTx(SerachSortPO po) {
		int i;
		i = serachSortWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	