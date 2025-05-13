package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserExtendPO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface UserExtendWriteDAO extends BaseWriteDAO<UserExtendPO> {

	int updateInvalidTime(@Param("po")UserExtendPO po);
	/**
	 * 根据手机号绑定微信OpenId
	 * @param mobile
	 * @param weiXinOpenId
	 * @return
	 */
	int bindingWeiXinOpenIdByMobileWithTx(@Param("mobile")String mobile, @Param("weiXinOpenId")String weiXinOpenId);
	/**
	 * 根据用户id绑定微信OpenId
	 * @param userId 用户id
	 * @param weiXinOpenId 微信OpenId
	 * @return
	 */
	int bindingWeiXinOpenIdByUserIdWithTx(@Param("userId")Long userId, @Param("weiXinOpenId")String weiXinOpenId);
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	int switchUserOpenIdWithTx(@Param("id")Long id, @Param("userId")Long userId);
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	int weixinSignoutWithTx(@Param("mobile")String mobile);
	
	int userOpenIdNullWithTx(@Param("userId")Long userId);
	/**
	 * 根据百度云推送channel_id绑定当前用户
	 * @param userId
	 * @param baiDuChannelId
	 * @param deviceType
	 * @return
	 */
	int bindingChannelIdByUserIdWithTx(@Param("userId")Long userId, @Param("baiDuChannelId")String baiDuChannelId, @Param("deviceType")Integer deviceType);
	/**
	 * 根据userId退出
	 * @param userId
	 * @return
	 */
	int signoutByUserIdWithTx(@Param("userId")Long userId);

    int activateUserByIds(@Param("userIds")List<Long> userIds);

	int updateReImportUserStatus(@Param("id")Long id);

	int deleteByIdWithTx(@Param("id")Long id);
}
	