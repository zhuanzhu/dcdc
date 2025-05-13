package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserQuitTempConverter;
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.components.user.manage.write.UserQuitTempWriteManage;
import com.egeo.components.user.po.UserQuitTempPO;
import com.egeo.components.user.service.write.UserQuitTempWriteService;

@Service("userQuitTempWriteService")
public class UserQuitTempWriteServiceImpl implements UserQuitTempWriteService {
	@Autowired
	private UserQuitTempWriteManage userQuitTempWriteManage;

	@Override
	public Long insertUserQuitTempWithTx(UserQuitTempDTO dto) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
		Long rt = userQuitTempWriteManage.insertUserQuitTempWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserQuitTempWithTx(UserQuitTempDTO dto) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
		int rt = userQuitTempWriteManage.updateUserQuitTempWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserQuitTempWithTx(UserQuitTempDTO dto) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
		int rt = userQuitTempWriteManage.deleteUserQuitTempWithTx(po);		
		return rt;
	}

	@Override
	public String insertUserQuitTempListWithTx(List<UserQuitTempDTO> userQuitTempDTOList) {
		String userQuitTempIds="";
		
		for (UserQuitTempDTO userQuitTempDTO : userQuitTempDTOList) {
			UserQuitTempPO po = UserQuitTempConverter.toPO(userQuitTempDTO);
			Long insertUserQuitId = userQuitTempWriteManage.insertUserQuitTempWithTx(po);
			
			userQuitTempIds = userQuitTempIds+","+insertUserQuitId;
		}
		return userQuitTempIds.substring(1);
	}

	@Override
	public int deleteUserQuitTempByParamsWithTx(UserQuitTempDTO quitTempDTO) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(quitTempDTO);
		int rt = userQuitTempWriteManage.deleteUserQuitTempByParamsWithTx(po);
		return rt;
	}
}
	