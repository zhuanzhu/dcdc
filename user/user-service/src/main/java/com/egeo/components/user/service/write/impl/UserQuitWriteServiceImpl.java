package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserQuitConverter;
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.components.user.manage.write.UserQuitWriteManage;
import com.egeo.components.user.po.UserQuitPO;
import com.egeo.components.user.service.write.UserQuitWriteService;


@Service("userQuitWriteService")
public class UserQuitWriteServiceImpl implements UserQuitWriteService {
	@Autowired
	private UserQuitWriteManage userQuitWriteManage;

	@Override
	public Long insertUserQuitWithTx(UserQuitDTO dto) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
		Long rt = userQuitWriteManage.insertUserQuitWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserQuitWithTx(UserQuitDTO dto) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
		int rt = userQuitWriteManage.updateUserQuitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserQuitWithTx(UserQuitDTO dto) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
		int rt = userQuitWriteManage.deleteUserQuitWithTx(po);		
		return rt;
	}
	/**
	 * 根据用户id集合删除用户离职信息表
	 * @param userIdInvalidSet
	 * @return
	 */
	@Override
	public int delByUserIdsWithTx(List<Long> userIdInvalidSet) {
		// TODO Auto-generated method stub
		return userQuitWriteManage.delByUserIdsWithTx(userIdInvalidSet);
	}
}
	