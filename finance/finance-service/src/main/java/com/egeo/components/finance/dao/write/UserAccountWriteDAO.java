package com.egeo.components.finance.dao.write;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountUpdatePO;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserAccountWriteDAO extends BaseWriteDAO<UserAccountPO> {

	/**
	 * 增加用户账户余额
	 * @param id 账户id
	 * @param sum 变动量
	 * @param ciphertext 新的加密值
	 * @return
	 */
	int increaseUserAccount(@Param("id")Long id, @Param("sum")BigDecimal sum,@Param("isRecharge")boolean isRecharge, @Param("salt") String salt);

	/**
	 * 批量更新用户账户余额
	 * @param aus
	 * @param isRecharge 
	 * @return
	 */
	int batchIncreaseUserAccount(@Param("aus")List<AccountUpdatePO> aus, @Param("isRecharge")boolean isRecharge);
	/**
	 * 根据用户id扣除积分账户
	 * @param userId
	 * @return
	 */
	int deductFoscoinAccountByUserId(@Param("userId")Long userId,@Param("ciphertext") String ciphertext, @Param("orderPaidByFubi")BigDecimal orderPaidByFubi);

	/**
	 * 批量修改用户账户有效性
	 * @param userIds
	 * @param diabled
	 * @return
	 */
	int batchUpdateUserAccountDisabled(@Param("ids")List<Long> userIds, @Param("disabled")int disabled);

	/**
	 * 更新用户账户有效性
	 * @param userId
	 * @param disabled
	 * @return
	 */
	int updateUserAccountDisabled(@Param("userId")Long userId, @Param("disabled")int disabled);

	/**
	 * 清理用户账户
	 * @param accountId
	 * @param salt
	 * @return
	 */
	int clearUserAccount(@Param("id")Long accountId, @Param("salt")String salt);

	int updateBeforeDisableBalanceWithTx(@Param("userId")Long userId);

    void updateAccountNameByUserId(@Param("id")String id,@Param("mail") String mail);
}
	