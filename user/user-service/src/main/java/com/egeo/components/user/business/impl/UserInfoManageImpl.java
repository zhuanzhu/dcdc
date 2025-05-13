package com.egeo.components.user.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserInfoManage;
import com.egeo.components.user.facade.UserInfoFacade;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userInfo")
public class UserInfoManageImpl implements UserInfoManage{

	
	@Resource(name="userInfoFacade")
	private UserInfoFacade userInfoFacade;

	@Override
	public UserInfoDTO findUserInfoById(UserInfoDTO dto) {
		return userInfoFacade.findUserInfoById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findUserInfoOfPage(UserInfoDTO dto, Pagination page) {
		return userInfoFacade.findUserInfoOfPage(dto, page);
	}

	@Override
	public List<UserInfoDTO> findUserInfoAll(UserInfoDTO dto) {
		return userInfoFacade.findUserInfoAll(dto);
	}

	@Override
	public Long insertUserInfoWithTx(UserInfoDTO dto) {
		return userInfoFacade.insertUserInfoWithTx(dto);
	}

	@Override
	public int updateUserInfoWithTx(UserInfoDTO dto) {
		return userInfoFacade.updateUserInfoWithTx(dto);
	}

	@Override
	public int deleteUserInfoWithTx(UserInfoDTO dto) {
		return userInfoFacade.deleteUserInfoWithTx(dto);
	}
	
	@Override
	public int findUserInfoSumByUserId(UserInfoDTO userInfoDTO) {
		// TODO Auto-generated method stub
		return userInfoFacade.findUserInfoSumByUserId(userInfoDTO);
	}
	
	@Override
	public int updateIsReadByIdWithTx(Long userInfoId) {
		return userInfoFacade.updateIsReadByIdWithTx(userInfoId);
	}


}
	