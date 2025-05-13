package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserExtendPO;

import java.util.List;

public interface UserExtendWriteManage {

    int save(UserExtendPO userExtendPO);

    int update(UserExtendPO userExtendPO);

	int deleteWithTx(UserExtendPO po);

	int deleteByIdWithTx(Long id);
	/**
     * 根据用户id修改用户扩展表信息(头像、真实姓名、性别、生日)
     * @param dto
     * @return
     */
	int updateUserExtendWithTx(UserExtendPO po);
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	int updateUserBackground(Long userId, String headPicUrl);

	int updateInvalidTime(UserExtendPO po);

	/**
	 * 更新用户拓展信息
	 * @param po
	 * @return
	 */
	int updateUserExtendInfoWithTx(UserExtendPO po);
	/**
	 * 根据手机号绑定微信OpenId
	 * @param mobile
	 * @param weiXinOpenId
	 * @return
	 */
	int bindingWeiXinOpenIdByMobileWithTx(String mobile, String weiXinOpenId);
	/**
	 * 根据用户id绑定微信OpenId
	 * @param userId 用户id
	 * @param weiXinOpenId 微信OpenId
	 * @return
	 */
	int bindingWeiXinOpenIdByUserIdWithTx(Long userId, String weiXinOpenId);
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	int switchUserOpenIdWithTx(Long id, Long userId);
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
     * @return
	 */
	int weixinSignoutWithTx(String mobile);
	/**
	 * 根据百度云推送channel_id绑定当前用户
	 * @param userId
	 * @param baiDuChannelId
	 * @param clientId
	 * @return
	 */
	int bindingChannelIdByUserIdWithTx(Long userId, String baiDuChannelId, Integer deviceType);
	/**
	 * 根据userId退出
	 * @param userId
	 * @return
	 */
	int signoutByUserIdWithTx(Long userId);

    int activateUserByIds(List<Long> userIds);

	int updateReImportUserStatus(Long id);
}
	