package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.KeywordWriteDAO;
import com.egeo.components.config.manage.write.KeywordWriteManage;
import com.egeo.components.config.po.KeywordPO;
import com.egeo.exception.BusinessException;

@Service
public class KeywordWriteManageImpl implements KeywordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private KeywordWriteDAO keywordWriteDAO;

	@Override
	public Long insertKeywordWithTx(KeywordPO po) {
		
		int i ;
		try {
				i = keywordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateKeywordWithTx(KeywordPO po) {
		int i;
		i = keywordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteKeywordWithTx(KeywordPO po) {
		int i;
		i = keywordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	