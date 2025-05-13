package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserQuitWriteManage;
import com.egeo.components.user.dao.write.UserQuitWriteDAO;
import com.egeo.components.user.po.UserQuitPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.StringUtils;

@Service
public class UserQuitWriteManageImpl implements UserQuitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserQuitWriteDAO userQuitWriteDAO;

	@Override
	public Long insertUserQuitWithTx(UserQuitPO po) {
		
		int i ;
		try {
				i = userQuitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserQuitWithTx(UserQuitPO po) {
		int i;
		i = userQuitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserQuitWithTx(UserQuitPO po) {
		int i;
		i = userQuitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据用户id集合删除用户离职信息表
	 * @param userIdInvalidSet
	 * @return
	 */
	@Override
	public int delByUserIdsWithTx(List<Long> userIdInvalidSet) {
		int i;
		i = StringUtils.isEmpty(userIdInvalidSet) ? 0 : userQuitWriteDAO.delByUserIdsWithTx(userIdInvalidSet);
		/*if (i == 0)
			throw new BusinessException("未能成功删除数据!");*/
		return i;
	}	
}
	