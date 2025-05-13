package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserTempManage {

	public UserTempDTO findUserTempById(UserTempDTO dto);	

	public PageResult<UserTempDTO> findUserTempOfPage(UserTempDTO dto,Pagination page);

	public List<UserTempDTO> findUserTempAll(UserTempDTO dto);

	Long insertUserTempWithTx(UserTempDTO dto);

	int updateUserTempWithTx(UserTempDTO dto);

	int deleteUserTempWithTx(UserTempDTO dto);
}
	