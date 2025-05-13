package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserQuitTempWriteManage;
import com.egeo.components.user.dao.write.UserQuitTempWriteDAO;
import com.egeo.components.user.po.UserQuitTempPO;
import com.egeo.exception.BusinessException;

@Service
public class UserQuitTempWriteManageImpl implements UserQuitTempWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserQuitTempWriteDAO userQuitTempWriteDAO;

	@Override
	public Long insertUserQuitTempWithTx(UserQuitTempPO po) {
		
		int i ;
		try {
				i = userQuitTempWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserQuitTempWithTx(UserQuitTempPO po) {
		int i;
		i = userQuitTempWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserQuitTempWithTx(UserQuitTempPO po) {
		int i;
		i = userQuitTempWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int deleteUserQuitTempByParamsWithTx(UserQuitTempPO po) {
		int i;
		i = userQuitTempWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
	