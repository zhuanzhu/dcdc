package com.egeo.components.finance.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.dto.UserAccountDTO;


public interface UserAccountWriteService {

	public Long insertUserAccountWithTx(UserAccountDTO dto);

	public int updateUserAccountWithTx(UserAccountDTO dto);

	public int deleteUserAccountWithTx(UserAccountDTO dto);
	/**
	 * 根据用户id扣除积分账户
	 * @param userId
	 * @return
	 */
	public int deductFoscoinAccountByUserIdWithTx(Long userId,String ciphertext,BigDecimal orderPaidByFubi);

	/**
	 * 批量修改用户账户有效性
	 * @param userIds
	 * @param i
	 * @return
	 */
	public int batchUpdateUserAccountDisabled(List<Long> userIds, int i);

	/**
	 * 更新用户账户有效性
	 * @param id
	 * @param i
	 */
	public int updateUserAccountDisabled(Long userId, int disabled);

	public int updateBeforeDisableBalanceWithTx(Long userId);

    void updateAccountNameByUserId(String id, String mail);
}
	