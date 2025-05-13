package com.egeo.components.user.manage.write.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.dao.read.ChannelActivityReadDAO;
import com.egeo.components.user.dao.read.ChannelReadDAO;
import com.egeo.components.user.dao.read.UserReadDAO;
import com.egeo.components.user.dao.read.UserRoleReadDAO;
import com.egeo.components.user.dao.read.UserWelfareReadDAO;
import com.egeo.components.user.dao.write.UserExtendWriteDAO;
import com.egeo.components.user.dao.write.UserPlatformWriteDAO;
import com.egeo.components.user.dao.write.UserRoleWriteDAO;
import com.egeo.components.user.dao.write.UserWelfareWriteDAO;
import com.egeo.components.user.dao.write.UserWriteDAO;
import com.egeo.components.user.manage.write.UserWriteManage;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.components.user.po.UserRolePO;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.StringUtils;

@Service
public class UserWriteManageImpl implements UserWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserWriteDAO userWriteDAO;

	@Autowired
	private UserReadDAO userReadDAO;

	@Autowired
	private UserWelfareReadDAO userWelfareReadDAO;

	@Autowired
	private UserWelfareWriteDAO userWelfareWriteDAO;

	@Autowired
	private ChannelReadDAO channelReadDAO;

	@Autowired
	private UserPlatformWriteDAO userPlatformWriteDAO;

	@Autowired
	private UserRoleWriteDAO userRoleWriteDAO;

	@Autowired
	private UserExtendWriteDAO userExtendWriteDAO;

	@Autowired
	private ChannelActivityReadDAO channelActivityReadDAO;

	@Autowired
	private UserRoleReadDAO userRoleReadDAO;

	@Override
	public int updateEncryptInfoByUserWithTx(UserPO po) {

		return userWriteDAO.update(po);
	}
	@Override
	public int updateUserInfo(UserPO po) {

		return userWriteDAO.update(po);
	}

	@Override
	public Long save(UserPO po) {
	    int i = userWriteDAO.insert(po);
	    if(i != 0){
	    	//保存用户平台关系表
			UserPlatformPO userPlatformPO = new UserPlatformPO();
			userPlatformPO.setUserId(po.getId());
			userPlatformPO.setPlatformId(po.getPlatformId());
			userPlatformWriteDAO.insert(userPlatformPO);
	    }
	    return po.getId();
	}

	@Override
	public void delete(Long id) {
		UserPO po = new UserPO();
		po.setId(id);
		int i = userWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
	}

	@Override
	public void deleteByImportIdWithTx(Long id,Long importId) {
		int i = userWriteDAO.deleteByImportIdWithTx(id,importId);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
	}

	public UserPO getIdUser(UserPO po){
		UserPO userPO = userReadDAO.findById(po);
		if(userPO != null){
			return userPO;
		}
		return null;


	}
    @Override
    public String saveOrUpdateWithTx(UserPO po, UserExtendPO userExtendPO, UserCookiePO userCookiePO) {

        return null;
    }
    @Override
    public int update(UserPO tar) {
        return userWriteDAO.update(tar);
    }
    /**
     * 用户注册
     * @param dto
     * @return
     */
	@Override
	public Long userRegisterWithTx(UserPO po,String shortCode) {
		Long userId = null;
		//根据邮箱查询用户信息
		List<UserCondition> list = userReadDAO.userByMail(po.getMail());
		if(list.size() > 1){
			throw new BusinessException(BusinessExceptionConstant.USER_NO_UNIQUE, "邮箱用户不唯一");
		}else if(list.size() == 0){
			throw new BusinessException(BusinessExceptionConstant.USER_NO_EXIST, "邮箱用户不存在");
		}else{
			userId = list.get(0).getId();
		}

		if (StringUtils.isEmpty(po.getChannelId())) {
			//根据渠道名称查询渠道id
			ChannelPO channelPO = new ChannelPO();
			channelPO.setShortCode(shortCode);
			List<ChannelPO> channelList = channelReadDAO.findAll(channelPO,null);
			if(StringUtils.isEmpty(channelList)){
				throw new BusinessException("请确认渠道短码正确");
			}
			po.setChannelId(channelList.get(0).getId());
		}

		//根据邮箱修改用户信息
		userWriteDAO.userRegister(po,new Date());
		if (StringUtils.isNotEmpty(userId)) {
			// 如果注册成功则进行授权
			if (po.getPlatformId().equals(7L)) {
				// 保存角色关系表
				UserRolePO userRolePO = new UserRolePO();
				userRolePO.setUserId(userId);
				userRolePO.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
				userRoleWriteDAO.insert(userRolePO);
			}
			if (po.getPlatformId().equals(2L)) {
				// 保存角色关系表
				UserRolePO userRolePO = new UserRolePO();
				userRolePO.setUserId(userId);
				userRolePO.setRoleId(BusinessConstant.PLATFORM2_USER_ROLEID);
				userRoleWriteDAO.insert(userRolePO);
			}
		}
		return userId;

	}
	/**
	 * 更新用户主表信息
	 */
	@Override
	public int updateUserWithTx(Long userId, Long companyId) {
		UserPO tar = new UserPO();
		tar.setId(userId);
		tar.setCompanyId(companyId);
		return userWriteDAO.update(tar);
	}
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @return
	 */
	@Override
	public int updateUserPasswordWithTx(String mail, String password,String salt) {

		return userWriteDAO.updateUserPassword(mail, password,salt);
	}
	@Override
	public Long saveUserMailWithTx(String mail,Long companyId) {
		UserPO po = new UserPO();
		po.setMail(mail);
		po.setCompanyId(companyId);
		userWriteDAO.insert(po);
		if(StringUtils.isEmpty(po.getId())){
			throw new BusinessException("未能成功添加用户数据");
		}else{
			return po.getId();
		}

	}
	//绑定手机号码
	@Override
	public int saveUserMobileWithTx(String mobile, Long userId,String weiXinOpenId) {
		int i = userWriteDAO.saveUserMobile(mobile,userId);
		if(i != 0){
			// 根据用户id绑定微信OpenId
			if(weiXinOpenId != null)
				userExtendWriteDAO.bindingWeiXinOpenIdByUserIdWithTx(userId, weiXinOpenId);
		}else{
			throw new BusinessException("绑定手机号失败，userId="+userId+",mobile="+mobile);
		}
		return i;
	}
	/**
	 * 根据用户邮箱设置支付密码
	 * @param mail
	 * @param password
	 * @return
	 */
	@Override
	public int setPaymentPasswordWithTx(String mail, String md5,String paymentCodeSaltId) {
		// TODO Auto-generated method stub
		return userWriteDAO.setPaymentPassword(mail, md5,paymentCodeSaltId);
	}
	@Override
	public Long updateAboutUser(List<UserTempPO> poList,int type) {
		long sum=0;
		List<String> mails = new ArrayList<>();
		//根据用户id查询是否员工部门信息不为空
		for (UserTempPO userTempPO : poList) {
			mails.add(userTempPO.getMail());
		}
		//根据邮箱集合查id集合
		List<Long> userIds = userReadDAO.mailsToUserIds(mails);

		int i = userWelfareReadDAO.findDepartmentIsNullSumByUserIds(userIds);
		if(i != 0){
			//是否跳出提示
			if(type == 1){
				throw new BusinessException(BusinessExceptionConstant.IS_NOT_COVER_DEPARTMENTS,"导入的文件中部分员工已完善部门信息，是否覆盖原有部门信息。");
			}

		}

		for (UserTempPO userTempPO : poList) {
			//根据邮箱查询用户
			UserPO userPO = new  UserPO();
			userPO.setMail(userTempPO.getMail());
			userPO.setIsDeleted(0);
			List<UserPO> userAll = userReadDAO.findAll(userPO,null);

			if(StringUtils.isNotEmpty(userAll)){
				UserWelfarePO userWelfare = userWelfareReadDAO.queryUserWelfareByUserId(userAll.get(0).getId());

				userWelfare.setDepartmentId(userTempPO.getDepartmentId());
				userWelfareWriteDAO.update(userWelfare);

				UserPO userPO2 = userAll.get(0);
				userPO2.setName(userTempPO.getName());
				userWriteDAO.update(userPO2);

			}
			sum = sum + 1;
		}
		return sum;
	}
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	@Override
	public int updateUserPasswordWithTx(Long userId, String password,String originalPassword) {
		//根据用户id查询用户信息
		UserPO po = new UserPO();
		po.setId(userId);
		UserPO userPO = userReadDAO.findById(po);
		if(!MD5Support.MD5(originalPassword, userPO.getSalt()).equals(userPO.getPassword())){
			throw new BusinessException("用户原密码不对");
		}
		String salt = SaltUtils.getRandomSalt();
		return userWriteDAO.updateUserPasswordWithTx(userId, MD5Support.MD5(password, salt),salt,new Date());
	}


	@Override
	public int resetManageUserPasswordWithTx(Long userId, String password) {
		//根据用户id查询用户信息
		UserPO po = new UserPO();
		po.setId(userId);
		UserPO userPO = userReadDAO.findManageById(po);
		if(userPO==null){
			throw new BusinessException("管理账号不存在");
		}
		String salt = SaltUtils.getRandomSalt();
		return userWriteDAO.updateUserPasswordWithTx(userId, MD5Support.MD5(password, salt),salt,new Date());
	}
	@Override
	public int updateQuitTime(UserPO po) {
		return userWriteDAO.updateQuitTime(po);
	}
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	@Override
	public int unbindByUserIdsWithTx(List<Long> userIdList) {
		return userWriteDAO.unbindByUserIdsWithTx(userIdList);
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
		return userWriteDAO.bindingMobileByBeforeMobileWithTx(mobile, beforeMobile,platformId);
	}
	/**
	 * 设置员工状态为离职和失效
	 * @param userId
	 * @return
	 */
	@Override
	public int updateIsAvailableAccountStatus(Long userId,Integer isAvailable) {
		// TODO Auto-generated method stub
		return userWriteDAO.updateIsAvailableAccountStatus(userId,isAvailable);
	}
	/**
	 * 批量保存用户信息
	 */
	@Override
	public List<UserPO> insertUserAllWithTx(List<UserTempPO> userTempList) {
		List<UserPO> users = new ArrayList<>();
		List<UserExtendPO> userExtends = new ArrayList<>();
		List<UserWelfarePO> userWelfares = new ArrayList<>();
		// 跟新和用户相关的表
		for (UserTempPO userTempPO : userTempList) {
			//1.插入到user表
			UserPO userPO = new UserPO();
			userPO.setMail(userTempPO.getMail());
			userPO.setLoginName(userTempPO.getMail());
			userPO.setCompanyId(userTempPO.getCompanyId());
			userPO.setMobile(userTempPO.getMobile());
			userPO.setPlatformId(userTempPO.getPlatformId());
			userWriteDAO.insert(userPO);

			//2.插入到user_extend
			UserExtendPO userExtendPO = new UserExtendPO();
			userExtendPO.setId(userPO.getId());
			userExtendPO.setSex(userTempPO.getSex());
			userExtendPO.setName(userTempPO.getName());
			userExtendPO.setNamePy(StringUtils.Hanyu2Pinyin(userTempPO.getName()));
			userExtendPO.setMemberCode(userTempPO.getMemberCode());
			userExtendPO.setBirthday(userTempPO.getBirthday());
			userExtends.add(userExtendPO);

			//3.插入到user_wefalre
			UserWelfarePO userWelfarePO = new UserWelfarePO();
			userWelfarePO.setUserId(userPO.getId());
			userWelfarePO.setDepartmentId(userTempPO.getDepartmentId());
			userWelfares.add(userWelfarePO);

			UserPO po = new UserPO();
			po.setId(userPO.getId());
			po.setMail(userTempPO.getMail());
			users.add(po);

		}
		return users;
	}
	/**
	 * 根据导入批次id查询导入用户数据同步到正式表中
	 */
	@Override
	public int syncUserAll(Long importId) {
		return userWriteDAO.syncUserAll(importId);
	}

	@Override
	public Long insertAllUserInfosWithTx(UserPO userPO, UserExtendPO userExtendPO, UserWelfarePO userWelfarePO) {
		Long userId = save(userPO);
		userExtendPO.setId(userId);
		if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(userPO.getPlatformId())) {
			//慢有优平台用户企业账号直接设置为启用
			userExtendPO.setStatus(1);
			UserRolePO userRolePO = new UserRolePO();
			userRolePO.setRoleId(BusinessConstant.PLATFORM2_USER_ROLEID);
			userRolePO.setUserId(userId);
			List<UserRolePO> roleList = userRoleReadDAO.findAll(userRolePO,null);
			if (StringUtils.isEmpty(roleList)) {
				userRoleWriteDAO.insert(userRolePO);
			}
		}else if(PlatformKeyConstant.FGJ_PLATFORM_ID.equals(userPO.getPlatformId())) {
			UserRolePO userRolePO = new UserRolePO();
			userRolePO.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
			userRolePO.setUserId(userId);
			List<UserRolePO> roleList = userRoleReadDAO.findAll(userRolePO,null);
			if (StringUtils.isEmpty(roleList)) {
				userRoleWriteDAO.insert(userRolePO);
			}
		}
		userWelfarePO.setUserId(userId);
		userWelfareWriteDAO.insert(userWelfarePO);
		userExtendWriteDAO.insert(userExtendPO);

		return userId;
	}

	@Override
	public Long updateAllUserInfosWithTx(UserPO userPO, UserExtendPO userExtendPO, UserWelfarePO userWelfarePO) {
		int i = userWriteDAO.update(userPO);
		if(StringUtils.isNotEmpty(userExtendPO.getIsAdministrator())){
			if(userExtendPO.getIsAdministrator() == 0){
				//根据用户id删除管理员角色
				userRoleWriteDAO.delByUserId(userExtendPO.getId());
			}
		}

		i += userExtendWriteDAO.update(userExtendPO);

		i += userWelfareWriteDAO.update(userWelfarePO);
		return new Long((long) i);
	}

	@Override
	public Long createUserWithTx(UserPO userPO, UserExtendPO userExtendPO) {
		Long userId = save(userPO);
		userExtendPO.setId(userId);
		int insert = userExtendWriteDAO.insert(userExtendPO);
		return userId;

	}

	@Override
	public int switchUserEnterpriseByCompanyId(UserPO userPO,Long companyNewId){
		if(userPO.getCompanyId()==null || userPO.getEnterpriseId() ==null){
			return 0;
		}
		return userWriteDAO.switchUserEnterpriseByCompanyId(userPO.getEnterpriseId(),userPO.getCompanyId(),userPO.getId(),companyNewId);
	}
}
