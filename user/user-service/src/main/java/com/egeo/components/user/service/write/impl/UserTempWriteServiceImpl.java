package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserTempConverter;
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.manage.write.UserTempWriteManage;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.components.user.service.write.UserTempWriteService;

@Service("userTempWriteService")
public class UserTempWriteServiceImpl implements UserTempWriteService {
	@Autowired
	private UserTempWriteManage userTempWriteManage;

	@Override
	public Long insertUserTempWithTx(UserTempDTO dto) {
		UserTempPO po = UserTempConverter.toPO(dto);
		Long rt = userTempWriteManage.insertUserTempWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserTempWithTx(UserTempDTO dto) {
		UserTempPO po = UserTempConverter.toPO(dto);
		int rt = userTempWriteManage.updateUserTempWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserTempWithTx(UserTempDTO dto) {
		UserTempPO po = UserTempConverter.toPO(dto);
		int rt = userTempWriteManage.deleteUserTempWithTx(po);		
		return rt;
	}

	@Override
	public int insertUserViewListWithTx(List<UserTempDTO> userTempDTOList) {
		List<UserTempPO> list = UserTempConverter.toPO(userTempDTOList);
		// 批量保存预导入用户信息
		return userTempWriteManage.insertUserTempAllWithTx(list);
	}
	/**
	 * 根据创建用户id清楚缓存数据
	 * @param createUserid
	 * @return
	 */
	@Override
	public int delByCreateUserId(Long createUserid) {
		// TODO Auto-generated method stub
		return userTempWriteManage.delByCreateUserId(createUserid);
	}

	@Override
	public int deleteUserTempByParamsWithTx(UserTempDTO tempDTO) {
		UserTempPO po = UserTempConverter.toPO(tempDTO);
		int rt = userTempWriteManage.deleteUserTempByParamsWithTx(po);
		return rt;
	}
}
	