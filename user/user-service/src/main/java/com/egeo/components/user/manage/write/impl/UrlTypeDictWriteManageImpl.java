package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UrlTypeDictWriteManage;
import com.egeo.components.user.dao.write.UrlTypeDictWriteDAO;
import com.egeo.components.user.po.UrlTypeDictPO;
import com.egeo.exception.BusinessException;

@Service
public class UrlTypeDictWriteManageImpl implements UrlTypeDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlTypeDictWriteDAO urlTypeDictWriteDAO;

	@Override
	public Long insertUrlTypeDictWithTx(UrlTypeDictPO po) {
		
		int i ;
		try {
				i = urlTypeDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUrlTypeDictWithTx(UrlTypeDictPO po) {
		int i;
		i = urlTypeDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUrlTypeDictWithTx(UrlTypeDictPO po) {
		int i;
		i = urlTypeDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	