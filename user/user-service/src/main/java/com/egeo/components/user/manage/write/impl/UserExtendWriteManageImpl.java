package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.UserRoleReadDAO;
import com.egeo.components.user.dao.write.UserExtendWriteDAO;
import com.egeo.components.user.dao.write.UserPlatformWriteDAO;
import com.egeo.components.user.dao.write.UserRoleWriteDAO;
import com.egeo.components.user.manage.write.UserExtendWriteManage;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.utils.StringUtils;

@Service
public class UserExtendWriteManageImpl implements UserExtendWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserExtendWriteDAO userExtendWriteDAO;
	
	@Autowired
	private UserRoleWriteDAO userRoleWriteDAO;
	
	@Autowired
	private UserRoleReadDAO userRoleReadDAO;
	
	@Autowired
	private UserPlatformWriteDAO userPlatformWriteDAO;

	@Override
	public int save(UserExtendPO userExtendPO) {
		return userExtendWriteDAO.insert(userExtendPO);
	}

	@Override
	public int update(UserExtendPO userExtendPO) {

		return userExtendWriteDAO.update(userExtendPO);
	}

	@Override
	public int deleteWithTx(UserExtendPO po) {

		return userExtendWriteDAO.delete(po);
	}

	@Override
	public int deleteByIdWithTx(Long id) {

		return userExtendWriteDAO.deleteByIdWithTx(id);
	}
	/**
	 * 根据用户id修改用户扩展表信息(头像、真实姓名、性别、生日)
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserExtendWithTx(UserExtendPO po) {
		if(StringUtils.isNotEmpty(po.getIsAdministrator())){
			if(po.getIsAdministrator() == 0){
				//根据用户id删除管理员角色
				userRoleWriteDAO.delByUserId(po.getId());
				//根据用户id查询角色id
				/**设为是否管理员时不操作用户平台数据BUG-FGJAPP-2503**/
				/*UserRolePO userRolePO = new UserRolePO();
				userRolePO.setUserId(po.getId());
				List<UserRolePO> userRoleList = userRoleReadDAO.findAll(userRolePO);
				List<Long> roleIds = new ArrayList<>();
				if(EmptyUtil.isEmpty(userRoleList)){
					UserPlatformPO userPlatformPO = new UserPlatformPO();
					userPlatformPO.setUserId(po.getId());
					userPlatformWriteDAO.deleteByPara(userPlatformPO);
				}else{
					for (UserRolePO userRolePO2 : userRoleList) {
						roleIds.add(userRolePO2.getRoleId());
					}
					if(EmptyUtil.isNotEmpty(roleIds)){
						// 根据角色id集合删除用户平台信息
						userPlatformWriteDAO.delByRoleIds(roleIds,po.getId());
					}
					
				}*/
				/**设为是否管理员时不操作用户平台数据BUG-FGJAPP-2503**/
			}
		}
		
		return userExtendWriteDAO.update(po);
	}
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserBackground(Long userId, String headPicUrl) {
		UserExtendPO po = new UserExtendPO();
		po.setId(userId);
		po.setHeadPicUrl(headPicUrl);
		return userExtendWriteDAO.update(po);
	}

	@Override
	public int updateInvalidTime(UserExtendPO po) {
		return userExtendWriteDAO.updateInvalidTime(po);
	}

	@Override
	public int updateUserExtendInfoWithTx(UserExtendPO po) {
		return userExtendWriteDAO.update(po);
	}
	/**
	 * 根据手机号绑定微信OpenId
	 * @param mobile
	 * @param weiXinOpenId
	 * @return
	 */
	@Override
	public int bindingWeiXinOpenIdByMobileWithTx(String mobile, String weiXinOpenId) {
		// TODO Auto-generated method stub
		return userExtendWriteDAO.bindingWeiXinOpenIdByMobileWithTx(mobile, weiXinOpenId);
	}

	@Override
	public int bindingWeiXinOpenIdByUserIdWithTx(Long userId, String weiXinOpenId) {
		// TODO Auto-generated method stub
		return userExtendWriteDAO.bindingWeiXinOpenIdByUserIdWithTx(userId, weiXinOpenId);
	}
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	@Override
	public int switchUserOpenIdWithTx(Long id, Long userId) {
		int i = userExtendWriteDAO.switchUserOpenIdWithTx(id, userId);
		if(!id.equals(userId))
			userExtendWriteDAO.userOpenIdNullWithTx(userId);
		return i;
	}
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	@Override
	public int weixinSignoutWithTx(String mobile) {
		// TODO Auto-generated method stub
		return userExtendWriteDAO.weixinSignoutWithTx(mobile);
	}

	@Override
	public int bindingChannelIdByUserIdWithTx(Long userId, String baiDuChannelId, Integer deviceType) {
		return userExtendWriteDAO.bindingChannelIdByUserIdWithTx(userId, baiDuChannelId, deviceType);
	}
	/**
	 * 根据userId退出
	 */
	@Override
	public int signoutByUserIdWithTx(Long userId) {
		return userExtendWriteDAO.signoutByUserIdWithTx(userId);
	}

	@Override
	public int activateUserByIds(List<Long> userIds) {
		return userExtendWriteDAO.activateUserByIds(userIds);
	}

	@Override
	public int updateReImportUserStatus(Long id) {
		return userExtendWriteDAO.updateReImportUserStatus(id);
	}
}
