package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UrlTypeWriteManage;
import com.egeo.components.user.dao.write.UrlTypeWriteDAO;
import com.egeo.components.user.po.UrlTypePO;
import com.egeo.exception.BusinessException;

@Service
public class UrlTypeWriteManageImpl implements UrlTypeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlTypeWriteDAO urlTypeWriteDAO;

	@Override
	public Long insertUrlTypeWithTx(UrlTypePO po) {
		
		int i ;
		try {
				i = urlTypeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUrlTypeWithTx(UrlTypePO po) {
		int i;
		i = urlTypeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUrlTypeWithTx(UrlTypePO po) {
		int i;
		i = urlTypeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	