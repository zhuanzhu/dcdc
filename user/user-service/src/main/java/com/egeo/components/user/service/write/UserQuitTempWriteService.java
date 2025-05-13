package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UserQuitTempDTO;


public interface UserQuitTempWriteService {

	public Long insertUserQuitTempWithTx(UserQuitTempDTO dto);

	public int updateUserQuitTempWithTx(UserQuitTempDTO dto);

	public int deleteUserQuitTempWithTx(UserQuitTempDTO dto);

	/**
	 * 将离职员工存储到临时表
	 * @param userQuitTempDTOList
	 * @return
	 */
	public String insertUserQuitTempListWithTx(List<UserQuitTempDTO> userQuitTempDTOList);

    int deleteUserQuitTempByParamsWithTx(UserQuitTempDTO quitTempDTO);
}
	