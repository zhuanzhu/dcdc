package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserQuitReadService {

	public UserQuitDTO findUserQuitById(UserQuitDTO dto);

	public PageResult<UserQuitDTO> findUserQuitOfPage(UserQuitDTO dto,Pagination page);

	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto);
}
	