package com.egeo.components.user.manage.write.impl;

import com.egeo.components.user.dao.write.ClientPayTypeConfigWriteDAO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.ClientWriteManage;
import com.egeo.components.user.dao.write.ClientWriteDAO;
import com.egeo.components.user.po.ClientPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ClientWriteManageImpl implements ClientWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ClientWriteDAO clientWriteDAO;
	@Autowired
	private ClientPayTypeConfigWriteDAO clientPayTypeConfigWriteDAO;

	@Override
	public Long insertClientWithTx(ClientPO po) {
		
		int i ;
		try {
				i = clientWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateClientWithTx(ClientPO po) {
		int i;
		i = clientWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteClientWithTx(ClientPO po) {
		int i;
		i = clientWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public boolean updateClientPayTypeWithTx(ClientPO clientPO, List<ClientPayTypeConfigPO> clientPayTypeConfigPOList) {
		int i = clientWriteDAO.update(clientPO);
		if(i==0){
			throw new BusinessException("未能成功更新数据");
		}
		for (ClientPayTypeConfigPO po : clientPayTypeConfigPOList) {
			int j = clientPayTypeConfigWriteDAO.update(po);
			if(j==0){
				throw new BusinessException("未能成功更新数据");
			}
		}
		return true;
	}
}
	