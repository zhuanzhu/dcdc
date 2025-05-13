package com.egeo.components.finance.dao.read;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.orm.BaseReadDAO;

public interface UserAccountReadDAO extends BaseReadDAO<UserAccountPO>{

	/**
	 * 查询用户所有账户
	 * @param userId
	 * @return
	 */
	List<UserAccountPO> queryUserAccountByUserId(@Param("userId")Long userId);

	/**
	 * 根据用户id和账户类型查询账户
	 * @param id
	 * @param type
	 * @return
	 */
	UserAccountPO queryUserAccountByUserIdAndType(@Param("id")Long id, @Param("type")Integer type);

	/**
	 * 根据id查询账户
	 * @param accountId
	 * @return
	 */
	UserAccountPO queryUserAccountById(@Param("id")Long accountId);
	List<UserAccountPO> queryPrdUserAccountAll();
	List<UserAccountPO> queryNowUserAccountAll();

    List<UserAccountPO> queryUserAccountByUserIds(@Param("userIdList")List<Long> userIdList);

	List<UserAccountPO> queryUserAccountByParam(@Param("po") UserAccountPO po);

	BigDecimal findBeforeDisabledBalance(@Param("userId") Long userId);
}
	