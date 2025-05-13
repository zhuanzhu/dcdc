package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserInformationReadManage {

	public UserInformationReadDTO findUserInformationReadById(UserInformationReadDTO dto);	

	public PageResult<UserInformationReadDTO> findUserInformationReadOfPage(UserInformationReadDTO dto,Pagination page);

	public List<UserInformationReadDTO> findUserInformationReadAll(UserInformationReadDTO dto);

	Long insertUserInformationReadWithTx(UserInformationReadDTO dto);

	int updateUserInformationReadWithTx(UserInformationReadDTO dto);

	int deleteUserInformationReadWithTx(UserInformationReadDTO dto);
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	public int deleteByUserInformationIdWithTx(Long userInformationId);
}
	