package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserWelfareManage {

	public UserWelfareDTO findUserWelfareById(UserWelfareDTO dto);	

	public PageResult<UserWelfareDTO> findUserWelfareOfPage(UserWelfareDTO dto,Pagination page);

	public List<UserWelfareDTO> findUserWelfareAll(UserWelfareDTO dto);

	Long insertUserWelfareWithTx(UserWelfareDTO dto);

	int updateUserWelfareWithTx(UserWelfareDTO dto);

	int deleteUserWelfareWithTx(UserWelfareDTO dto);
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	public int updateUserWelfareByUserIdWithTx(UserWelfareDTO dto);
}
	