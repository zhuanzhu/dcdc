package com.egeo.components.finance.manage.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.po.UserAccountPO;


public interface UserAccountWriteManage {

	Long insertUserAccountWithTx(UserAccountPO po);

	int updateUserAccountWithTx(UserAccountPO po);

	int deleteUserAccountWithTx(UserAccountPO po);
	/**
	 * 根据用户id扣除积分账户
	 * @param userId
	 * @return
	 */
	int deductFoscoinAccountByUserIdWithTx(Long userId,String ciphertext, BigDecimal orderPaidByFubi);

	/**
	 * 批量修改用户账户有效性
	 * @param userIds
	 * @param diabled
	 * @return
	 */
	int batchUpdateUserAccountDisabled(List<Long> userIds, int disabled);

	/**
	 * 更新用户账户有效性
	 * @param userId
	 * @param disabled
	 * @return
	 */
	int updateUserAccountDisabled(Long userId, int disabled);

	public int updateBeforeDisableBalanceWithTx(Long userId);

    void updateAccountNameByUserId(String id, String mail);
}
	