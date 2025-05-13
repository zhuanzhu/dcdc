package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserTempManage;
import com.egeo.components.user.facade.UserTempFacade;
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userTemp")
public class UserTempManageImpl implements UserTempManage{

	
	@Resource(name="userTempFacade")
	private UserTempFacade userTempFacade;

	@Override
	public UserTempDTO findUserTempById(UserTempDTO dto) {
		return userTempFacade.findUserTempById(dto);
	}

	@Override
	public PageResult<UserTempDTO> findUserTempOfPage(UserTempDTO dto, Pagination page) {
		return userTempFacade.findUserTempOfPage(dto, page);
	}

	@Override
	public List<UserTempDTO> findUserTempAll(UserTempDTO dto) {
		return userTempFacade.findUserTempAll(dto);
	}

	@Override
	public Long insertUserTempWithTx(UserTempDTO dto) {
		return userTempFacade.insertUserTempWithTx(dto);
	}

	@Override
	public int updateUserTempWithTx(UserTempDTO dto) {
		return userTempFacade.updateUserTempWithTx(dto);
	}

	@Override
	public int deleteUserTempWithTx(UserTempDTO dto) {
		return userTempFacade.deleteUserTempWithTx(dto);
	}


}
	