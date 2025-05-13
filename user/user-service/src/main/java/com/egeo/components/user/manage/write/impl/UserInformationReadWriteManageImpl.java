package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserInformationReadWriteManage;
import com.egeo.components.user.dao.write.UserInformationReadWriteDAO;
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.exception.BusinessException;

@Service
public class UserInformationReadWriteManageImpl implements UserInformationReadWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInformationReadWriteDAO userInformationReadWriteDAO;

	@Override
	public Long insertUserInformationReadWithTx(UserInformationReadPO po) {
		
		int i ;
		try {
				i = userInformationReadWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserInformationReadWithTx(UserInformationReadPO po) {
		int i;
		i = userInformationReadWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserInformationReadWithTx(UserInformationReadPO po) {
		int i;
		i = userInformationReadWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	@Override
	public int deleteByUserInformationIdWithTx(Long userInformationId) {
		// TODO Auto-generated method stub
		return userInformationReadWriteDAO.deleteByUserInformationIdWithTx(userInformationId);
	}	
}
	