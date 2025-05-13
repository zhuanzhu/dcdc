package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserInfoWriteManage;
import com.egeo.components.user.dao.read.UserInfoReadDAO;
import com.egeo.components.user.dao.write.UserInfoWriteDAO;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.StringUtils;

@Service
public class UserInfoWriteManageImpl implements UserInfoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoWriteDAO userInfoWriteDAO;
	
	@Autowired
	private UserInfoReadDAO userInfoReadDAO;

	@Override
	public Long insertUserInfoWithTx(UserInfoPO po) {
		
		int i ;
		try {
				i = userInfoWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserInfoWithTx(UserInfoPO po) {
		int i;
		i = userInfoWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserInfoWithTx(UserInfoPO po) {
		int i;
		i = userInfoWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateIsReadByIdWithTx(Long userInfoId) {
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setId(userInfoId);
		userInfoPO.setIsRead(1);
		return userInfoWriteDAO.update(userInfoPO);
	}

	@Override
	public int updateByUserIdType(Long userId, Long type, Long platformId) {
		List<Long> userInfoIds = userInfoReadDAO.findByUserIdType(userId, type, platformId);
		if(StringUtils.isNotEmpty(userInfoIds)){
			return userInfoWriteDAO.updateByUserInfoIds(userInfoIds);
		}
		return 0;
	}	
}
	