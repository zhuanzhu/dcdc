package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.ClientPayTypeConfigWriteManage;
import com.egeo.components.user.dao.write.ClientPayTypeConfigWriteDAO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.exception.BusinessException;

@Service
public class ClientPayTypeConfigWriteManageImpl implements ClientPayTypeConfigWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ClientPayTypeConfigWriteDAO clientPayTypeConfigWriteDAO;

	@Override
	public Long insertClientPayTypeConfigWithTx(ClientPayTypeConfigPO po) {
		
		int i ;
		try {
				i = clientPayTypeConfigWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateClientPayTypeConfigWithTx(ClientPayTypeConfigPO po) {
		int i;
		i = clientPayTypeConfigWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteClientPayTypeConfigWithTx(ClientPayTypeConfigPO po) {
		int i;
		i = clientPayTypeConfigWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	