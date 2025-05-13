package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SuSerachKeywordWriteManage;
import com.egeo.components.product.dao.write.SuSerachKeywordWriteDAO;
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.exception.BusinessException;

@Service
public class SuSerachKeywordWriteManageImpl implements SuSerachKeywordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuSerachKeywordWriteDAO suSerachKeywordWriteDAO;

	@Override
	public Long insertSuSerachKeywordWithTx(SuSerachKeywordPO po) {
		
		int i ;
		try {
				i = suSerachKeywordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSuSerachKeywordWithTx(SuSerachKeywordPO po) {
		int i;
		i = suSerachKeywordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSuSerachKeywordWithTx(SuSerachKeywordPO po) {
		int i;
		i = suSerachKeywordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	