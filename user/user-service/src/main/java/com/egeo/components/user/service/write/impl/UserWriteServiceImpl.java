package com.egeo.components.user.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.components.user.service.write.UserWelfareWriteService;
import com.egeo.components.user.service.write.UserWriteService;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.converter.UserTempConverter;
import com.egeo.components.user.converter.UserWelfareConverter;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.manage.write.UserWriteManage;
import com.egeo.components.user.po.UserPO;

import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.StringUtils;

@Service("userWriteService")
public class UserWriteServiceImpl implements UserWriteService {
	@Autowired
	private UserWriteManage userWriteManage;

	@Autowired
	private UserReadManage userReadManage;

	@Autowired
	private UserExtendWriteService userExtendWriteService;

	@Autowired
	private UserPlatformWriteService userPlatformWriteService;

	@Autowired
	private UserWelfareWriteService userWelfareWriteService;

	@Override
	public int updateEncryptInfoByUserWithTx(UserDTO updUser) {

		return userWriteManage.updateEncryptInfoByUserWithTx(UserConverter.toPO(updUser));
	}

	@Override
	public int updateUserInfoWithTx(UserDTO user) {

		return userWriteManage.updateUserInfo(UserConverter.toPO(user));
	}

	public String saveOrUpdateWithTx(UserDTO dto, UserExtendDTO userExtendDTO, UserCookieDTO userCookieDTO) {
		UserPO po = UserConverter.toPO(dto);
		UserPO tar = userWriteManage.getIdUser(po);
		if (tar != null) {
			// 强制用户不能修改账号
			po.setLoginName(null);

			if(StringUtils.isNotEmpty(po.getMobile())){
				// 根据手机查询用户
				List<UserPO> manageList = userReadManage.userByManage(po.getMobile());
				if (manageList.size() > 0 && !manageList.get(0).getId().equals(po.getId())) {
					throw new BusinessException("该手机已被注册");
				}
			}

			if(StringUtils.isNotEmpty(po.getMail())){
				// 根据邮箱查询用户
				UserPO mail = userReadManage.userByMail(po.getMail());
				if (mail != null && !mail.getId().equals(po.getId())) {
					throw new BusinessException("该邮箱已被注册");
				}
			}

			if (po.getPassword() != null && !po.getPassword().equals("")) {
				if (po.getPassword().length() < 6) {
					throw new BusinessException(BusinessExceptionConstant.GREATERSIX, "密码必须大于6位");
				}
				// 字符串是否与正则表达式相匹配
				if (!com.egeo.utils.str.StringUtils.isNotPasswordMay(po.getPassword())) {
					throw new BusinessException(BusinessExceptionConstant.LETTER_FIGURE_SYMBOL,
							"密码必须包含大写字母、小写字母、数字和符号其中三项！");
				}
				String salt = SaltUtils.getRandomSalt();
				String md5 = MD5Support.MD5(po.getPassword(), salt);
				po.setPassword(md5);
				po.setSalt(salt);
			} else {
				po.setPassword(null);
			}

			userWriteManage.update(po);
			// 修改扩展表信息
			userExtendWriteService.updateWithTx(userExtendDTO);
			return "编辑用户成功";
		} else {
			if (po.getLoginName() == null) {
				throw new BusinessException(BusinessExceptionConstant.USER_NAME_NO_PASSWORD, "帐号不能为空！");
			}
			List<UserPO> list = userReadManage.userByloginName(po);
			if (list != null) {
				throw new BusinessException(BusinessExceptionConstant.ROLE_EXIST, "用户已存在");
			}
			if(StringUtils.isNotEmpty(po.getMobile())){
				// 根据手机查询用户
				List<UserPO> manageList = userReadManage.userByManage(po.getMobile());
				if (manageList.size() > 0 && !manageList.get(0).getId().equals(po.getId())) {
					throw new BusinessException("该手机已被注册");
				}
			}

			if(StringUtils.isNotEmpty(po.getMail())){
				// 根据邮箱查询用户
				UserPO mail = userReadManage.userByMail(po.getMail());
				if (mail != null && !mail.getId().equals(po.getId())) {
					throw new BusinessException("该邮箱已被注册");
				}
			}

			if (po.getPassword().length() < 6) {
				throw new BusinessException(BusinessExceptionConstant.GREATERSIX, "密码必须大于6位");
			}
			// 字符串是否与正则表达式相匹配
			if (!com.egeo.utils.str.StringUtils.isNotPasswordMay(po.getPassword())) {
				throw new BusinessException(BusinessExceptionConstant.LETTER_FIGURE_SYMBOL,
						"密码必须包含大写字母、小写字母、数字和符号其中三项！");
			}

			String salt = SaltUtils.getRandomSalt();
			po.setPassword(MD5Support.MD5(po.getPassword(), salt));
			po.setSalt(salt);
			Long userId = userWriteManage.save(po);

			// 添加用户平台信息
			UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
			userPlatformDTO.setUserId(userId);
			userPlatformDTO.setPlatformId(dto.getPlatformId());
			userPlatformWriteService.saveWithTx(userPlatformDTO);

			// 添加扩展表信息
			userExtendDTO.setId(userId);
			userExtendWriteService.saveWithTx(userExtendDTO);
			return "添加用户成功";
		}

	}

	@Override
	public void deleteWithTx(UserDTO dto) {
		/*Assert.notNull(dto, -1);
		Assert.notNull(dto.getId(), -1, "");*/
		userWriteManage.delete(dto.getId());
	}

	@Override
	public void deleteByImportIdWithTx(Long id,Long importId) {
		userWriteManage.deleteByImportIdWithTx(id,importId);
	}
	/**
     * 用户注册
     * @param dto
     * @return
     */
	@Override
	public Long userRegisterWithTx(UserDTO dto,String shortCode) {
		UserPO po = UserConverter.toPO(dto);
		String salt = SaltUtils.getRandomSalt();
		po.setPassword(MD5Support.MD5(po.getPassword(), salt));
		po.setSalt(salt);
		return userWriteManage.userRegisterWithTx(po,shortCode);
	}
	/**
	 * 更新用户主表信息
	 */
	@Override
	public int updateUserWithTx(Long userId, Long companyId) {

		return userWriteManage.updateUserWithTx(userId, companyId);
	}
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @return
	 */
	@Override
	public int updateUserPassword(String mail, String password) {
		String salt = SaltUtils.getRandomSalt();
		return userWriteManage.updateUserPasswordWithTx(mail, MD5Support.MD5(password, salt),salt);
	}
	/**
	 * 添加用户邮箱信息返回用户id
	 * @param mail
	 * @return
	 */
	@Override
	public Long saveUserMail(String mail,Long companyId) {

		return userWriteManage.saveUserMailWithTx(mail,companyId);
	}

	/**
	 * 账户绑定手机号
	 * @param mail
	 * @return
	 */
	@Override
	public int saveUserMobile(String mobile, Long userId,String weiXinOpenId) {

		return userWriteManage.saveUserMobileWithTx(mobile,userId,weiXinOpenId);
	}
	/**
	 * 根据用户邮箱设置支付密码
	 * @param mail
	 * @param password
	 * @return
	 */
	@Override
	public int setPaymentPassword(String mail, String md5,String paymentCodeSaltId ) {
		if(StringUtils.isEmpty(mail)){
			throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY, "邮箱不能为空");
		}
		if(StringUtils.isEmpty(md5)){
			throw new BusinessException("加密盐不能为空");
		}
		if(StringUtils.isEmpty(paymentCodeSaltId)){
			throw new BusinessException("加密盐uuid不能为空");
		}
		return userWriteManage.setPaymentPasswordWithTx(mail, md5,paymentCodeSaltId);
	}


	@Override
	public List<UserDTO> userIdassureImportUserAboutUserWithTx(List<UserTempDTO> userTempList, String userOrderInfo) {
		// 批量保存用户信息
		List<UserPO> list = userWriteManage.insertUserAllWithTx(UserTempConverter.toPO(userTempList));
		List<UserDTO> userDTOList=new ArrayList<>();
		// 跟新和用户相关的表
		for (UserTempDTO userTempDTO : userTempList) {
			//1.插入到user表
			UserPO userPO = new UserPO();
			userPO.setMail(userTempDTO.getMail());
			userPO.setLoginName(userTempDTO.getMail());
			userPO.setCompanyId(userTempDTO.getCompanyId());
			userPO.setMobile(userTempDTO.getMobile());
			userPO.setPlatformId(userTempDTO.getPlatformId());
			Long userId = userWriteManage.save(userPO);

			//2.插入到user_extend
			UserExtendDTO userExtendDTO = new UserExtendDTO();
			userExtendDTO.setId(userId);
			userExtendDTO.setSex(userTempDTO.getSex());
			userExtendDTO.setName(userTempDTO.getName());
			userExtendDTO.setNamePy(com.egeo.utils.str.StringUtils.Hanyu2Pinyin(userTempDTO.getName()));
			userExtendDTO.setMemberCode(userTempDTO.getMemberCode());
			userExtendDTO.setBirthday(userTempDTO.getBirthday());
			userExtendWriteService.saveWithTx(userExtendDTO);

			//3.插入到user_wefalre
			UserWelfareDTO userWelfareDTO = new UserWelfareDTO();
			userWelfareDTO.setUserId(userId);
			userWelfareDTO.setDepartmentId(userTempDTO.getDepartmentId());
			userWelfareWriteService.insertUserWelfareWithTx(userWelfareDTO);

			UserDTO dto = new UserDTO();
			dto.setId(userId);
			dto.setMail(userTempDTO.getMail());
			userDTOList.add(dto);

		}
		return userDTOList;
	}

	@Override
	public Long insertUserWithTx(UserDTO userDTO) {
		return userWriteManage.save(UserConverter.toPO(userDTO));
	}

	@Override
	public Long updateAboutUser(List<UserTempDTO> userTempList,int type) {

		return userWriteManage.updateAboutUser(UserTempConverter.toPO(userTempList),type);
	}
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	@Override
	public int updateUserPasswordWithTx(Long userId, String password,String originalPassword) {
		// TODO Auto-generated method stub
		return userWriteManage.updateUserPasswordWithTx(userId, password,originalPassword);
	}

	@Override
	public int updateQuitTime(UserDTO dto) {
		return userWriteManage.updateQuitTime(UserConverter.toPO(dto));
	}
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	@Override
	public int unbindByUserIds(List<Long> userIdList,Long platformId) {
		UserPO userPO = userReadManage.findUserByID(userIdList.get(0));
		String mobile = userPO.getMobile();
		if(StringUtils.isEmpty(mobile))
			throw new BusinessException("解绑用户手机号码为空，用户id="+userIdList.get(0));

		userWriteManage.unbindByUserIdsWithTx(userIdList);
		List<UserPO> list = userReadManage.findListByManage(mobile, platformId);
		return list.size();
	}
	/**
	 * 根据用户之前手机号统一换绑手机
	 * @param mobile
	 * @param beforeMobile
	 * @param platformId
	 * @return
	 */
	@Override
	public int bindingMobileByBeforeMobileWithTx(String mobile, String beforeMobile, Long platformId) {
		// TODO Auto-generated method stub
		return userWriteManage.bindingMobileByBeforeMobileWithTx(mobile, beforeMobile,platformId);
	}
	/**
	 * 设置员工状态为离职和失效
	 * @param userId
	 * @return
	 */
	@Override
	public int updateIsAvailableAccountStatus(Long userId,Integer isAvailable) {
		// TODO Auto-generated method stub
		return userWriteManage.updateIsAvailableAccountStatus(userId,isAvailable);
	}
	/**
	 * 根据导入批次id查询导入用户数据同步到正式表中
	 */
	@Override
	public int syncUserAll(Long importId) {
		// TODO Auto-generated method stub
		return userWriteManage.syncUserAll(importId);
	}

	@Override
	public Long insertAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO) {
		return userWriteManage.insertAllUserInfosWithTx(UserConverter.toPO(userDTO), UserExtendConverter.toPO(userExtendDTO), UserWelfareConverter.toPO(userWelfareDTO));
	}

	@Override
	public Long updateAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO) {
		return userWriteManage.updateAllUserInfosWithTx(UserConverter.toPO(userDTO), UserExtendConverter.toPO(userExtendDTO), UserWelfareConverter.toPO(userWelfareDTO));
	}

	@Override
	public Long createUserWithTx(UserDTO userDTO, UserExtendDTO extendDTO) {
		return userWriteManage.createUserWithTx(UserConverter.toPO(userDTO),UserExtendConverter.toPO(extendDTO));
	}

	@Override
	public int resetManageUserPasswordWithTx(Long userId, String password) {
		// TODO Auto-generated method stub
		return userWriteManage.resetManageUserPasswordWithTx(userId, password);
	}

	@Override
	public int switchUserEnterpriseByCompanyId(UserDTO userDTO,Long companyNewId){

		return userWriteManage.switchUserEnterpriseByCompanyId(UserConverter.toPO(userDTO),companyNewId);
	}
}
