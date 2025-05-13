package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserWelfareConverter;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.manage.write.UserWelfareWriteManage;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.components.user.service.write.UserWelfareWriteService;

@Service("userWelfareWriteService")
public class UserWelfareWriteServiceImpl implements UserWelfareWriteService {
	@Autowired
	private UserWelfareWriteManage userWelfareWriteManage;

	@Override
	public Long insertUserWelfareWithTx(UserWelfareDTO dto) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
		Long rt = userWelfareWriteManage.insertUserWelfareWithTx(po);		
		return rt;
	}

	@Override
	public int updateUserWelfareWithTx(UserWelfareDTO dto) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
		int rt = userWelfareWriteManage.updateUserWelfareWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUserWelfareWithTx(UserWelfareDTO dto) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
		int rt = userWelfareWriteManage.deleteUserWelfareWithTx(po);		
		return rt;
	}
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserWelfareByUserIdWithTx(UserWelfareDTO dto) {
		
		return userWelfareWriteManage.updateUserWelfareByUserIdWithTx(UserWelfareConverter.toPO(dto));
	}

	@Override
	public void refreshUserDayPraiseCount() {
		
		userWelfareWriteManage.refreshUserDayPraiseCountWithTx();
	}

	@Override
	public void refreshUserMonthPraiseCount() {
		userWelfareWriteManage.refreshUserMonthPraiseCountWithTx();
	}

	@Override
	public int increasePraiseCount(Long userId) {
		
		return userWelfareWriteManage.increasePraiseCountWithTx(userId);
	}
}
	