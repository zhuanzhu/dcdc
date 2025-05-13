package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserQuitTempManage;
import com.egeo.components.user.facade.UserQuitTempFacade;
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userQuitTemp")
public class UserQuitTempManageImpl implements UserQuitTempManage{

	
	@Resource(name="userQuitTempFacade")
	private UserQuitTempFacade userQuitTempFacade;

	@Override
	public UserQuitTempDTO findUserQuitTempById(UserQuitTempDTO dto) {
		return userQuitTempFacade.findUserQuitTempById(dto);
	}

	@Override
	public PageResult<UserQuitTempDTO> findUserQuitTempOfPage(UserQuitTempDTO dto, Pagination page) {
		return userQuitTempFacade.findUserQuitTempOfPage(dto, page);
	}

	@Override
	public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO dto) {
		return userQuitTempFacade.findUserQuitTempAll(dto);
	}

	@Override
	public Long insertUserQuitTempWithTx(UserQuitTempDTO dto) {
		return userQuitTempFacade.insertUserQuitTempWithTx(dto);
	}

	@Override
	public int updateUserQuitTempWithTx(UserQuitTempDTO dto) {
		return userQuitTempFacade.updateUserQuitTempWithTx(dto);
	}

	@Override
	public int deleteUserQuitTempWithTx(UserQuitTempDTO dto) {
		return userQuitTempFacade.deleteUserQuitTempWithTx(dto);
	}


}
	