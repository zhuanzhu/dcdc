package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserInformationReadConverter;
import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.components.user.manage.write.UserInformationReadWriteManage;
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.components.user.service.write.UserInformationReadWriteService;

@Service("userInformationReadWriteService")
public class UserInformationReadWriteServiceImpl implements UserInformationReadWriteService {
	@Autowired
	private UserInformationReadWriteManage userInformationReadWriteManage;

	@Override
	public Long insertUserInformationReadWithTx(UserInformationReadDTO dto) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
		Long rt = userInformationReadWriteManage.insertUserInformationReadWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserInformationReadWithTx(UserInformationReadDTO dto) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
		int rt = userInformationReadWriteManage.updateUserInformationReadWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserInformationReadWithTx(UserInformationReadDTO dto) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
		int rt = userInformationReadWriteManage.deleteUserInformationReadWithTx(po);		
		return rt;
	}
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	@Override
	public int deleteByUserInformationIdWithTx(Long userInformationId) {
		// TODO Auto-generated method stub
		return userInformationReadWriteManage.deleteByUserInformationIdWithTx(userInformationId);
	}
}
	