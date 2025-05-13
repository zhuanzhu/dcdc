package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserInfoConverter;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.manage.write.UserInfoWriteManage;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.components.user.service.write.UserInfoWriteService;

@Service("userInfoWriteService")
public class UserInfoWriteServiceImpl implements UserInfoWriteService {
	@Autowired
	private UserInfoWriteManage userInfoWriteManage;

	@Override
	public Long insertUserInfoWithTx(UserInfoDTO dto) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
		Long rt = userInfoWriteManage.insertUserInfoWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserInfoWithTx(UserInfoDTO dto) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
		int rt = userInfoWriteManage.updateUserInfoWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserInfoWithTx(UserInfoDTO dto) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
		int rt = userInfoWriteManage.deleteUserInfoWithTx(po);		
		return rt;
	}
	/**
	 * 根据用户消息id更新用户消息是否已读状态
	 */
	@Override
	public int updateIsReadByIdWithTx(Long userInfoId) {
		return userInfoWriteManage.updateIsReadByIdWithTx(userInfoId);
	}

	@Override
	public int updateByUserIdType(Long userId, Long type, Long platformId) {
		return userInfoWriteManage.updateByUserIdType(userId, type, platformId);
	}
}
	