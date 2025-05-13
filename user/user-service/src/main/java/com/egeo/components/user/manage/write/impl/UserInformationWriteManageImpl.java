package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserInformationWriteManage;
import com.egeo.components.user.dao.write.UserInformationWriteDAO;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.exception.BusinessException;

@Service
public class UserInformationWriteManageImpl implements UserInformationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInformationWriteDAO userInformationWriteDAO;

	@Override
	public Long insertUserInformationWithTx(UserInformationPO po) {
		
		int i ;
		try {
				i = userInformationWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserInformationWithTx(UserInformationPO po) {
		int i;
		i = userInformationWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserInformationWithTx(UserInformationPO po) {
		int i;
		i = userInformationWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	