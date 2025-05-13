package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserQuitManage;
import com.egeo.components.user.facade.UserQuitFacade;
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userQuit")
public class UserQuitManageImpl implements UserQuitManage{

	
	@Resource(name="userQuitFacade")
	private UserQuitFacade userQuitFacade;

	@Override
	public UserQuitDTO findUserQuitById(UserQuitDTO dto) {
		return userQuitFacade.findUserQuitById(dto);
	}

	@Override
	public PageResult<UserQuitDTO> findUserQuitOfPage(UserQuitDTO dto, Pagination page) {
		return userQuitFacade.findUserQuitOfPage(dto, page);
	}

	@Override
	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto) {
		return userQuitFacade.findUserQuitAll(dto);
	}

	@Override
	public Long insertUserQuitWithTx(UserQuitDTO dto) {
		return userQuitFacade.insertUserQuitWithTx(dto);
	}

	@Override
	public int updateUserQuitWithTx(UserQuitDTO dto) {
		return userQuitFacade.updateUserQuitWithTx(dto);
	}

	@Override
	public int deleteUserQuitWithTx(UserQuitDTO dto) {
		return userQuitFacade.deleteUserQuitWithTx(dto);
	}


}
	