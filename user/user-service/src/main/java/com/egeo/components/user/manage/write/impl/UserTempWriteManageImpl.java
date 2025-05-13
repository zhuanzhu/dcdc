package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserTempWriteManage;
import com.egeo.components.user.dao.write.UserTempWriteDAO;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.exception.BusinessException;

@Service
public class UserTempWriteManageImpl implements UserTempWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserTempWriteDAO userTempWriteDAO;

	@Override
	public Long insertUserTempWithTx(UserTempPO po) {
		
		int i ;
		try {
				i = userTempWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserTempWithTx(UserTempPO po) {
		int i;
		i = userTempWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserTempWithTx(UserTempPO po) {
		int i;
		i = userTempWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据创建用户id清楚缓存数据
	 * @param createUserid
	 * @return
	 */
	@Override
	public int delByCreateUserId(Long createUserid) {
		UserTempPO userTempPO = new UserTempPO();
		userTempPO.setCreateUserid(createUserid);
		return userTempWriteDAO.deleteByPara(userTempPO);
	}
	/**
	 * 批量保存预导入用户信息
	 */
	@Override
	public int insertUserTempAllWithTx(List<UserTempPO> list) {
		// TODO Auto-generated method stub
		return userTempWriteDAO.insertUserTempAllWithTx(list);
	}

	@Override
	public int deleteUserTempByParamsWithTx(UserTempPO po) {
		int i;
		i = userTempWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
	