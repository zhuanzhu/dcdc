package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.SessionReferrerWriteManage;
import com.egeo.components.user.dao.write.SessionReferrerWriteDAO;
import com.egeo.components.user.po.SessionReferrerPO;
import com.egeo.exception.BusinessException;

@Service
public class SessionReferrerWriteManageImpl implements SessionReferrerWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionReferrerWriteDAO sessionReferrerWriteDAO;

	@Override
	public Long insertSessionReferrerWithTx(SessionReferrerPO po) {
		
		int i ;
		try {
				i = sessionReferrerWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSessionReferrerWithTx(SessionReferrerPO po) {
		int i;
		i = sessionReferrerWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSessionReferrerWithTx(SessionReferrerPO po) {
		int i;
		i = sessionReferrerWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	