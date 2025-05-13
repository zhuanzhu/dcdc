package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserCookieConverter;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.manage.read.UserCookieReadManage;
import com.egeo.components.user.manage.write.UserCookieWriteManage;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.service.write.UserCookieWriteService;


@Service("userCookieWriteService")
public class UserCookieWriteServiceImpl implements UserCookieWriteService {
	@Autowired
	private UserCookieWriteManage userCookieWriteManage;
	
	@Autowired
        private UserCookieReadManage userCookieReadManage;

	@Override
	public void saveUserCookieWithTx(UserCookieDTO userCookieDTO) {
		
		userCookieWriteManage.saveUserCookieWithTx(UserCookieConverter.toPO(userCookieDTO));
	}

    @Override
    public int updateWithTx(UserCookieDTO userCookieDTO) {
        UserCookiePO userCookiePO = UserCookieConverter.toPO(userCookieDTO);
        
        return userCookieWriteManage.update(userCookiePO);
    }

	@Override
	public void deleteCookieUserbyUserIdWithTx(Long userId) {
		userCookieWriteManage.deleteCookieUserbyUserIdWithTx(userId);
		
	}
	/**
	 * 根据用户id集合删除用户cookie信息
	 * @param userIds
	 * @return
	 */
	@Override
	public int delByUsetIds(List<Long> userIds) {
		// TODO Auto-generated method stub
		return userCookieWriteManage.delByUsetIdsWithTx(userIds);
	}
}
	