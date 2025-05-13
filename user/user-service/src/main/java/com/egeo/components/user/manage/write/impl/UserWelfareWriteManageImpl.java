package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserWelfareWriteManage;
import com.egeo.components.user.dao.write.UserWelfareWriteDAO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.exception.BusinessException;

@Service
public class UserWelfareWriteManageImpl implements UserWelfareWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserWelfareWriteDAO userWelfareWriteDAO;

	@Override
	public Long insertUserWelfareWithTx(UserWelfarePO po) {
		
		int i ;
		try {
				i = userWelfareWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserWelfareWithTx(UserWelfarePO po) {
		int i;
		i = userWelfareWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserWelfareWithTx(UserWelfarePO po) {
		int i;
		i = userWelfareWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserWelfareByUserIdWithTx(UserWelfarePO po) {
		int i;
		i = userWelfareWriteDAO.updateUserWelfareByUserIdWithTx(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int refreshUserDayPraiseCountWithTx() {
		return userWelfareWriteDAO.refreshUserDayPraiseCount();
	}

	@Override
	public int refreshUserMonthPraiseCountWithTx() {
		return userWelfareWriteDAO.refreshUserMonthPraiseCount();
	}

	@Override
	public int increasePraiseCountWithTx(Long userId) {
		return userWelfareWriteDAO.increasePraiseCount(userId);
	}
}
	