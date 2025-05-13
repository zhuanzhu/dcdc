package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserQuitManage {

	public UserQuitDTO findUserQuitById(UserQuitDTO dto);	

	public PageResult<UserQuitDTO> findUserQuitOfPage(UserQuitDTO dto,Pagination page);

	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto);

	Long insertUserQuitWithTx(UserQuitDTO dto);

	int updateUserQuitWithTx(UserQuitDTO dto);

	int deleteUserQuitWithTx(UserQuitDTO dto);
}
	