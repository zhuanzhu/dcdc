package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.InfoWriteManage;
import com.egeo.components.user.dao.write.InfoSendWayWriteDAO;
import com.egeo.components.user.dao.write.InfoWriteDAO;
import com.egeo.components.user.po.InfoPO;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.exception.BusinessException;

@Service
public class InfoWriteManageImpl implements InfoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoWriteDAO infoWriteDAO;
	
	@Autowired
	private InfoSendWayWriteDAO infoSendWayWriteDAO;

	@Override
	public Long insertInfoWithTx(InfoPO po,List<Long> sendWayIds) {
		
		int i ;
		try {
				i = infoWriteDAO.insert(po);
				for (Long sendWayId : sendWayIds) {
					InfoSendWayPO infoSendWayPO = new InfoSendWayPO();
					infoSendWayPO.setInfoTemplateId(po.getId());
					infoSendWayPO.setSendWayId(sendWayId);
					infoSendWayPO.setPlatformId(po.getPlatformId());
					infoSendWayWriteDAO.insert(infoSendWayPO);
				}
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateInfoWithTx(InfoPO po) {
		int i;
		i = infoWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInfoWithTx(InfoPO po) {
		int i;
		i = infoWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	