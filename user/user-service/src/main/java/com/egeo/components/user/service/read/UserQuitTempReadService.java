package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserQuitTempReadService {

	public UserQuitTempDTO findUserQuitTempById(UserQuitTempDTO dto);

	public PageResult<UserQuitTempDTO> findUserQuitTempOfPage(UserQuitTempDTO dto,Pagination page);

	public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO dto);
}
	