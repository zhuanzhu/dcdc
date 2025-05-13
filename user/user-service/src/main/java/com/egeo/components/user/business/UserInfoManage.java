package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserInfoManage {

	public UserInfoDTO findUserInfoById(UserInfoDTO dto);	

	public PageResult<Map<String, Object>> findUserInfoOfPage(UserInfoDTO dto,Pagination page);

	public List<UserInfoDTO> findUserInfoAll(UserInfoDTO dto);

	Long insertUserInfoWithTx(UserInfoDTO dto);

	int updateUserInfoWithTx(UserInfoDTO dto);

	int deleteUserInfoWithTx(UserInfoDTO dto);
	/**
	 * 根据当前用户id查询当前用户消息数量
	 * @param userInfoDTO
	 * @return
	 */
	public int findUserInfoSumByUserId(UserInfoDTO userInfoDTO);
	/**
	 * 根据用户消息id更新用户消息是否已读状态
	 * @param userInfoId
	 * @return
	 */
	public int updateIsReadByIdWithTx(Long userInfoId);
}
	