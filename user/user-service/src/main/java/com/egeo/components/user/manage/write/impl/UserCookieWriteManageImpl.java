package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserCookieWriteManage;
import com.egeo.components.user.dao.write.UserCookieWriteDAO;
import com.egeo.components.user.po.UserCookiePO;

@Service
public class UserCookieWriteManageImpl implements UserCookieWriteManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserCookieWriteDAO userCookieWriteDAO;

    @Override
    public void saveUserCookieWithTx(UserCookiePO po) {
        
        userCookieWriteDAO.insert(po);
    }

    @Override
    public int update(UserCookiePO po) {
        
        return userCookieWriteDAO.update(po);
    }

	@Override
	public void deleteCookieUserbyUserIdWithTx(Long userId) {
		UserCookiePO po = new UserCookiePO();
		po.setUserId(userId);
		userCookieWriteDAO.deleteByPara(po);
		
	}
	/**
	 * 根据用户id集合删除用户cookie信息
	 * @param userIds
	 * @return
	 */
	@Override
	public int delByUsetIdsWithTx(List<Long> userIds) {
		// TODO Auto-generated method stub
		return userCookieWriteDAO.delByUsetIds(userIds);
	}


}
