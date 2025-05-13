package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.LocalParamWriteManage;
import com.egeo.components.cms.dao.write.LocalParamWriteDAO;
import com.egeo.components.cms.po.LocalParamPO;
import com.egeo.exception.BusinessException;

@Service
public class LocalParamWriteManageImpl implements LocalParamWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LocalParamWriteDAO localParamWriteDAO;

	@Override
	public Long insertLocalParamWithTx(LocalParamPO po) {
		
		int i ;
		try {
				i = localParamWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLocalParamWithTx(LocalParamPO po) {
		int i;
		i = localParamWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLocalParamWithTx(LocalParamPO po) {
		int i;
		i = localParamWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	