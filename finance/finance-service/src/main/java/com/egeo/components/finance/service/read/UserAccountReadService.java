package com.egeo.components.finance.service.read;


import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserAccountReadService {

	public UserAccountDTO findUserAccountById(UserAccountDTO dto);

	public PageResult<UserAccountDTO> findUserAccountOfPage(UserAccountDTO dto,Pagination page);

	public List<UserAccountDTO> findUserAccountAll(UserAccountDTO dto);

	/**
	 * 查询用户所有账户
	 * @param userId
	 * @return
	 */
	public List<UserAccountDTO> queryUserAccountByUserId(Long userId);

	/**
	 * 查询用户的某个账户
	 * @param id
	 * @param type
	 * @return
	 */
	public UserAccountDTO queryUserAccountByUserIdAndType(Long id, Integer type);

    List<UserAccountDTO> queryUserAccountByUserIds(List<Long> userIdList);

	public List<UserAccountDTO> queryUserAccountByParam(UserAccountDTO dto);

	public BigDecimal findBeforeDisabledBalance(Long userId);

}
	