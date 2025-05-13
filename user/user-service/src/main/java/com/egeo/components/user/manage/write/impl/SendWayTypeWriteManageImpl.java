package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.SendWayTypeWriteManage;
import com.egeo.components.user.dao.write.SendWayTypeWriteDAO;
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.exception.BusinessException;

@Service
public class SendWayTypeWriteManageImpl implements SendWayTypeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SendWayTypeWriteDAO sendWayTypeWriteDAO;

	@Override
	public Long insertSendWayTypeWithTx(SendWayTypePO po) {
		
		int i ;
		try {
				i = sendWayTypeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSendWayTypeWithTx(SendWayTypePO po) {
		int i;
		i = sendWayTypeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSendWayTypeWithTx(SendWayTypePO po) {
		int i;
		i = sendWayTypeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	