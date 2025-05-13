package com.egeo.components.user.service.read;


import java.util.List;

import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserInfoReadService {

	public UserInfoDTO findUserInfoById(UserInfoDTO dto);

	public PageResult<UserInfoDTO> findUserInfoOfPage(UserInfoDTO dto,Pagination page);

	public List<UserInfoDTO> findUserInfoAll(UserInfoDTO dto);
	/**
	 * 根据消息id查询用户数量
	 * @param infoId
	 * @param platformId
	 * @return
	 */
	public int findUserSumByInfoId(Long infoId, Long platformId);
	/**
	 * 根据消息id分页查询用户信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<UserExtendDTO> findUserByInfoIdOfPage(UserInfoDTO dto, Pagination page);
	/**
	 * 根据当前用户id查询当前用户消息数量
	 * @param userInfoDTO
	 * @return
	 */
	public int findUserInfoSumByUserId(UserInfoDTO userInfoDTO);
}
	