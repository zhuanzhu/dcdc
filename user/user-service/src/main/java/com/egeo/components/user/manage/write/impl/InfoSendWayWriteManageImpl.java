package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.InfoSendWayWriteManage;
import com.egeo.components.user.dao.write.InfoSendWayWriteDAO;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.exception.BusinessException;

@Service
public class InfoSendWayWriteManageImpl implements InfoSendWayWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoSendWayWriteDAO infoSendWayWriteDAO;

	@Override
	public Long insertInfoSendWayWithTx(InfoSendWayPO po) {
		
		int i ;
		try {
				i = infoSendWayWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateInfoSendWayWithTx(InfoSendWayPO po) {
		int i;
		i = infoSendWayWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInfoSendWayWithTx(InfoSendWayPO po) {
		int i;
		i = infoSendWayWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	