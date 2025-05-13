package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.manage.write.UserExtendWriteManage;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.GetToken;
import com.egeo.utils.StringUtils;

@Service("userExtendWriteService")
public class UserExtendWriteServiceImpl implements UserExtendWriteService {
	@Autowired
	private UserExtendWriteManage userExtendWriteManage;
	@Autowired
	private UserExtendReadManage userExtendReadManage;
	@Autowired
	private GetToken getToken;
    @Override
    public int saveWithTx(UserExtendDTO userExtendDTO) {
        return userExtendWriteManage.save(UserExtendConverter.toPO(userExtendDTO));
    }

    @Override
    public int updateWithTx(UserExtendDTO userExtendDTO) {
        return userExtendWriteManage.update(UserExtendConverter.toPO(userExtendDTO));
    }

	@Override
	public int deleteWithTx(UserExtendDTO userExtendDTO) {

		return userExtendWriteManage.deleteWithTx(UserExtendConverter.toPO(userExtendDTO));
	}

	@Override
	public int deleteByIdWithTx(Long id) {

		return userExtendWriteManage.deleteByIdWithTx(id);
	}
	/**
     * 根据用户id修改用户扩展表信息(头像、真实姓名、性别、生日)
     * @param dto
     * @return
     */
	@Override
	public int updateUserExtendWithTx(UserExtendDTO dto) {
		
		return userExtendWriteManage.updateUserExtendWithTx(UserExtendConverter.toPO(dto));
	}
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserBackground(Long userId, String headPicUrl) {
		UserExtendPO userExtendPO = userExtendReadManage.findById(userId);
		if(StringUtils.isNotEmpty(userExtendPO.getHeadPicUrl())){
			String[] split = userExtendPO.getHeadPicUrl().split("/");
			int length = split.length;
			String string = split[length - 1];
			int i = getToken.deletePicture(string);
			if (i != 200) {
				throw new BusinessException("删除七牛图片失败");
			}
		}
		
		return userExtendWriteManage.updateUserBackground(userId, headPicUrl);
	}

	@Override
	public int updateInvalidTime(UserExtendDTO dto) {
		return userExtendWriteManage.updateInvalidTime(UserExtendConverter.toPO(dto));
	}

	@Override
	public int updateUserExtendInfoWithTx(UserExtendDTO dto) {
		return userExtendWriteManage.updateUserExtendInfoWithTx(UserExtendConverter.toPO(dto));
	}
	/**
	 * 根据手机号绑定微信OpenId
	 * @param mobile
	 * @param weiXinOpenId
	 * @return
	 */
	@Override
	public int bindingWeiXinOpenIdByMobileWithTx(String mobile, String weiXinOpenId) {
		if(StringUtils.isEmpty(mobile))
			throw new BusinessException("请填写手机号");
		if(StringUtils.isEmpty(weiXinOpenId))
			throw new BusinessException("请填写微信OpenId");
		return userExtendWriteManage.bindingWeiXinOpenIdByMobileWithTx(mobile, weiXinOpenId) ;
	}
	/**
	 * 根据用户id绑定微信OpenId
	 * @param userId 用户id
	 * @param weiXinOpenId 微信OpenId
	 * @return
	 */
	@Override
	public int bindingWeiXinOpenIdByUserIdWithTx(Long userId, String weiXinOpenId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("请填写用户id");
		if(StringUtils.isEmpty(weiXinOpenId))
			throw new BusinessException("请填写微信OpenId");
		return userExtendWriteManage.bindingWeiXinOpenIdByUserIdWithTx(userId, weiXinOpenId);
	}
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	@Override
	public int switchUserOpenIdWithTx(Long id, Long userId) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请填写当前用户id");
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("请填写上次登录用户id");
		return userExtendWriteManage.switchUserOpenIdWithTx(id, userId);
	}
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	@Override
	public int weixinSignoutWithTx(String mobile) {
		if(StringUtils.isEmpty(mobile))
			throw new BusinessException("请绑定手机号码");
		return userExtendWriteManage.weixinSignoutWithTx(mobile);
	}

	@Override
	public int bindingChannelIdByUserIdWithTx(Long userId, String baiDuChannelId,Integer deviceType) {
		return userExtendWriteManage.bindingChannelIdByUserIdWithTx(userId, baiDuChannelId,deviceType);
	}

	@Override
	public int signoutByUserIdWithTx(Long userId) {
		return userExtendWriteManage.signoutByUserIdWithTx(userId);
	}

	@Override
	public int activateUserByIds(List<Long> userIds) {
		return userExtendWriteManage.activateUserByIds(userIds);
	}

	@Override
	public int updateReImportUserStatus(Long id) {
		return userExtendWriteManage.updateReImportUserStatus(id);
	}
}
	