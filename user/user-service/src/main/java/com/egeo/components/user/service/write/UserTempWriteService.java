package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UserTempDTO;


public interface UserTempWriteService {

	public Long insertUserTempWithTx(UserTempDTO dto);

	public int updateUserTempWithTx(UserTempDTO dto);

	public int deleteUserTempWithTx(UserTempDTO dto);

	public int insertUserViewListWithTx(List<UserTempDTO> userTempDTOList);
	/**
	 * 根据创建用户id清楚缓存数据
	 * @param createUserid
	 * @return
	 */
	public int delByCreateUserId(Long createUserid);

    int deleteUserTempByParamsWithTx(UserTempDTO tempDTO);
}
	