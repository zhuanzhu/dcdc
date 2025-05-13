package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserExtendDTO;

import java.util.List;

public interface UserExtendWriteService {

    int saveWithTx(UserExtendDTO userExtendDTO);

    int updateWithTx(UserExtendDTO userExtendDTO);

	int deleteWithTx(UserExtendDTO userExtendDTO);

	int deleteByIdWithTx(Long id);
	/**
     * 根据用户id修改用户扩展表信息(头像、真实姓名、性别、生日)
     * @param dto
     * @return
     */
	int updateUserExtendWithTx(UserExtendDTO dto);
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	int updateUserBackground(Long userId, String headPicUrl);

	/**
	 * 设置员工的失效时间
	 * @param dto
	 * @return
	 */
	int updateInvalidTime(UserExtendDTO dto);

	/**
	 * 更新用户拓展信息
	 * @param userExtend
	 * @return
	 */
	int updateUserExtendInfoWithTx(UserExtendDTO dto);
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
	 * 根据用户mobile注销微信登录
	 * @param id
	 * @param userId
     * @return
	 */
	int weixinSignoutWithTx(String mobile);
	/**
	 * 根据百度云推送channel_id绑定当前用户
	 * @param userId
	 * @param baiDuChannelId
	 * @return
	 */
	int bindingChannelIdByUserIdWithTx(Long userId, String baiDuChannelId,Integer deviceType);
	/**
	 * 根据userId退出
	 * @param userId
	 * @return
	 */
	int signoutByUserIdWithTx(Long userId);

    int activateUserByIds(List<Long> userIds);

	int updateReImportUserStatus(Long id);
}
	