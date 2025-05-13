package com.egeo.components.finance.service.write.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.UserAccountConverter;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.manage.write.UserAccountWriteManage;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.components.finance.service.write.UserAccountWriteService;

@Service("userAccountWriteService")
public class UserAccountWriteServiceImpl implements UserAccountWriteService {
	@Autowired
	private UserAccountWriteManage userAccountWriteManage;

	@Override
	public Long insertUserAccountWithTx(UserAccountDTO dto) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
		Long rt = userAccountWriteManage.insertUserAccountWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserAccountWithTx(UserAccountDTO dto) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
		int rt = userAccountWriteManage.updateUserAccountWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserAccountWithTx(UserAccountDTO dto) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
		int rt = userAccountWriteManage.deleteUserAccountWithTx(po);		
		return rt;
	}
	/**
	 * 根据用户id扣除积分账户
	 * @param userId
	 * @return
	 */
	@Override
	public int deductFoscoinAccountByUserIdWithTx(Long userId,String ciphertext, BigDecimal orderPaidByFubi) {
		return userAccountWriteManage.deductFoscoinAccountByUserIdWithTx(userId,ciphertext, orderPaidByFubi);
	}

	@Override
	public int batchUpdateUserAccountDisabled(List<Long> userIds, int disabled) {
		
		return userAccountWriteManage.batchUpdateUserAccountDisabled(userIds,disabled);
	}

	@Override
	public int updateUserAccountDisabled(Long userId, int disabled) {
		
		return userAccountWriteManage.updateUserAccountDisabled(userId,disabled);
	}

	@Override
	public int updateBeforeDisableBalanceWithTx(Long userId) {

		return userAccountWriteManage.updateBeforeDisableBalanceWithTx(userId);
	}

	@Override
	public void updateAccountNameByUserId(String id, String mail) {
		userAccountWriteManage.updateAccountNameByUserId( id, mail);
	}
}
	