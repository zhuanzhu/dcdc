package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserInformationReadReadService {

	public UserInformationReadDTO findUserInformationReadById(UserInformationReadDTO dto);

	public PageResult<UserInformationReadDTO> findUserInformationReadOfPage(UserInformationReadDTO dto,Pagination page);

	public List<UserInformationReadDTO> findUserInformationReadAll(UserInformationReadDTO dto);
}
	