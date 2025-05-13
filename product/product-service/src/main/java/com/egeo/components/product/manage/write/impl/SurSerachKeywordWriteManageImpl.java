package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SurSerachKeywordWriteManage;
import com.egeo.components.product.dao.write.SurSerachKeywordWriteDAO;
import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.exception.BusinessException;

@Service
public class SurSerachKeywordWriteManageImpl implements SurSerachKeywordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SurSerachKeywordWriteDAO surSerachKeywordWriteDAO;

	@Override
	public Long insertSurSerachKeywordWithTx(SurSerachKeywordPO po) {
		
		int i ;
		try {
				i = surSerachKeywordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSurSerachKeywordWithTx(SurSerachKeywordPO po) {
		int i;
		i = surSerachKeywordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSurSerachKeywordWithTx(SurSerachKeywordPO po) {
		int i;
		i = surSerachKeywordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	