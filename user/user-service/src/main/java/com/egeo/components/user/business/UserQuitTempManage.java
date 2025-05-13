package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserQuitTempManage {

	public UserQuitTempDTO findUserQuitTempById(UserQuitTempDTO dto);	

	public PageResult<UserQuitTempDTO> findUserQuitTempOfPage(UserQuitTempDTO dto,Pagination page);

	public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO dto);

	Long insertUserQuitTempWithTx(UserQuitTempDTO dto);

	int updateUserQuitTempWithTx(UserQuitTempDTO dto);

	int deleteUserQuitTempWithTx(UserQuitTempDTO dto);
}
	