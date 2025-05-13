package com.egeo.components.finance.manage.read;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserAccountReadManage {

	public UserAccountPO findUserAccountById(UserAccountPO po);

	public PageResult<UserAccountPO> findUserAccountOfPage(UserAccountPO po,Pagination page);

	public List<UserAccountPO> findUserAccountAll(UserAccountPO po);

	/**
	 * 查询用户所有账户
	 * @param userId
	 * @return
	 */
	public List<UserAccountPO> queryUserAccountByUserId(Long userId);

	/**
	 * 查询用户的某个账户
	 * @param id
	 * @param type
	 * @return
	 */
	public UserAccountPO queryUserAccountByUserIdAndType(Long id, Integer type);

	/**
	 * 根据id查询用户账户
	 * @param accountId
	 * @return
	 */
	public UserAccountPO queryUserAccountById(Long accountId);

    List<UserAccountPO> queryUserAccountByUserIds(List<Long> userIdList);

	List<UserAccountPO> queryUserAccountByParam(UserAccountPO po);

	BigDecimal findBeforeDisabledBalance(Long userId);
}
	