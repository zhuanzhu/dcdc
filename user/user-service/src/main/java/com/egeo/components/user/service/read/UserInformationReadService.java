package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UserInformationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserInformationReadService {

	public UserInformationDTO findUserInformationById(UserInformationDTO dto);

	public PageResult<UserInformationDTO> findUserInformationOfPage(UserInformationDTO dto,Pagination page);

	public List<UserInformationDTO> findUserInformationAll(UserInformationDTO dto);
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<UserInformationDTO> findUserInformationOfByUserIdPage(UserInformationDTO dto, Pagination page);
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public int findUnreadByUserId(UserInformationDTO dto);
}
	