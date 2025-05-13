package com.egeo.components.finance.manage.write.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.UserAccountWriteDAO;
import com.egeo.components.finance.manage.write.UserAccountWriteManage;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.utils.EmptyUtil;

@Service
public class UserAccountWriteManageImpl implements UserAccountWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAccountWriteDAO userAccountWriteDAO;

	@Override
	public Long insertUserAccountWithTx(UserAccountPO po) {
		
		int i ;
		try {
				i = userAccountWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUserAccountWithTx(UserAccountPO po) {
		int i;
		i = userAccountWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserAccountWithTx(UserAccountPO po) {
		int i;
		i = userAccountWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据用户id扣除积分账户
	 * @param userId
	 * @return
	 */
	@Override
	public int deductFoscoinAccountByUserIdWithTx(Long userId,String ciphertext, BigDecimal orderPaidByFubi) {
		return userAccountWriteDAO.deductFoscoinAccountByUserId(userId,ciphertext, orderPaidByFubi);
	}

	@Override
	public int batchUpdateUserAccountDisabled(List<Long> userIds, int disabled) {
		if(EmptyUtil.isNotEmpty(userIds)){
			return userAccountWriteDAO.batchUpdateUserAccountDisabled(userIds,disabled);
		}else{
			return 0;
		}
		
	}

	@Override
	public int updateUserAccountDisabled(Long userId, int disabled) {
		return userAccountWriteDAO.updateUserAccountDisabled(userId,disabled);
	}

	@Override
	public int updateBeforeDisableBalanceWithTx(Long userId) {
		return userAccountWriteDAO.updateBeforeDisableBalanceWithTx(userId);
	}

	@Override
	public void updateAccountNameByUserId(String id, String mail) {
		userAccountWriteDAO.updateAccountNameByUserId( id, mail);
	}
}
	