package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ExternalLinkWriteManage;
import com.egeo.components.cms.dao.write.ExternalLinkWriteDAO;
import com.egeo.components.cms.po.ExternalLinkPO;
import com.egeo.exception.BusinessException;

@Service
public class ExternalLinkWriteManageImpl implements ExternalLinkWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExternalLinkWriteDAO externalLinkWriteDAO;

	@Override
	public Long insertExternalLinkWithTx(ExternalLinkPO po) {
		
		int i ;
		try {
				i = externalLinkWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateExternalLinkWithTx(ExternalLinkPO po) {
		int i;
		i = externalLinkWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteExternalLinkWithTx(ExternalLinkPO po) {
		int i;
		i = externalLinkWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	