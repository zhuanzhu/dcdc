package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserInformationConverter;
import com.egeo.components.user.dto.UserInformationDTO;
import com.egeo.components.user.manage.write.UserInformationWriteManage;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.components.user.service.write.UserInformationWriteService;

@Service("userInformationWriteService")
public class UserInformationWriteServiceImpl implements UserInformationWriteService {
	@Autowired
	private UserInformationWriteManage userInformationWriteManage;

	@Override
	public Long insertUserInformationWithTx(UserInformationDTO dto) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
		Long rt = userInformationWriteManage.insertUserInformationWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserInformationWithTx(UserInformationDTO dto) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
		int rt = userInformationWriteManage.updateUserInformationWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserInformationWithTx(UserInformationDTO dto) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
		int rt = userInformationWriteManage.deleteUserInformationWithTx(po);		
		return rt;
	}
}
	