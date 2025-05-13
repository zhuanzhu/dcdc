package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserInformationReadManage;
import com.egeo.components.user.facade.UserInformationReadFacade;
import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userInformationRead")
public class UserInformationReadManageImpl implements UserInformationReadManage{

	
	@Resource(name="userInformationReadFacade")
	private UserInformationReadFacade userInformationReadFacade;

	@Override
	public UserInformationReadDTO findUserInformationReadById(UserInformationReadDTO dto) {
		return userInformationReadFacade.findUserInformationReadById(dto);
	}

	@Override
	public PageResult<UserInformationReadDTO> findUserInformationReadOfPage(UserInformationReadDTO dto, Pagination page) {
		return userInformationReadFacade.findUserInformationReadOfPage(dto, page);
	}

	@Override
	public List<UserInformationReadDTO> findUserInformationReadAll(UserInformationReadDTO dto) {
		return userInformationReadFacade.findUserInformationReadAll(dto);
	}

	@Override
	public Long insertUserInformationReadWithTx(UserInformationReadDTO dto) {
		return userInformationReadFacade.insertUserInformationReadWithTx(dto);
	}

	@Override
	public int updateUserInformationReadWithTx(UserInformationReadDTO dto) {
		return userInformationReadFacade.updateUserInformationReadWithTx(dto);
	}

	@Override
	public int deleteUserInformationReadWithTx(UserInformationReadDTO dto) {
		return userInformationReadFacade.deleteUserInformationReadWithTx(dto);
	}
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	@Override
	public int deleteByUserInformationIdWithTx(Long userInformationId) {
		// TODO Auto-generated method stub
		return userInformationReadFacade.deleteByUserInformationIdWithTx(userInformationId);
	}


}
	