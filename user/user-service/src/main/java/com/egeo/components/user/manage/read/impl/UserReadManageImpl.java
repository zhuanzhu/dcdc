package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.dao.read.UserExtendReadDAO;
import com.egeo.components.user.dao.read.UserReadDAO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service
public class UserReadManageImpl implements UserReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserReadDAO userReadDAO;
    @Autowired
    private UserExtendReadDAO uerDAO;


    @Override
    public UserPO findUniqueUser(UserPO po) {
        List<UserCondition> rt = userReadDAO.findUniqueUser(po,null);
        if (rt.size() > 1) {
            throw new BusinessException(BusinessExceptionConstant.USER_NO_UNIQUE, "账户异常，请联系客服解决");
        } else if (rt.size() == 0) {
        	if(StringUtils.isNotEmpty(po.getMail())){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_EXIST, "邮箱未认证");
        	}else if(StringUtils.isNotEmpty(po.getMobile())){
        		throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY, "手机号码未绑定邮箱，请使用邮箱登录");
        	}else{
        		throw new BusinessException(BusinessExceptionConstant.USER_NO_EXIST, "用户不存在");
        	}
        }
        UserCondition user = null;
        for (UserCondition userCondition : rt) {
			if(userCondition.getAccountStatus() ==null || userCondition.getAccountStatus() == 0){
				user = userCondition;
			}
		}
        if(StringUtils.isEmpty(user)){
        	throw new BusinessException(BusinessExceptionConstant.ACCOUNT_EXPIRED,"账户已失效");
        }
        if(user.getStatus() == 0){
        	throw new BusinessException(BusinessExceptionConstant.ACCOUNT_NO_REGISTER,"请先注册");
        }

        return user;
    }

    @Override
    public UserPO findUserByID(Long userId) {

        UserPO po = new UserPO();
        po.setId(userId);
        return userReadDAO.findById(po);
    }

    public PageResult<UserPO> findPage(Pagination page, UserPO po) {
        int cnt = userReadDAO.count(po,null);
        List<UserPO> list = new ArrayList<UserPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = userReadDAO.page(po, page);
        }
        PageResult<UserPO> pageResult = new PageResult<UserPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }

    @Override
    public PageResult<UserCondition> findPageUser(Pagination page, UserPO po, UserExtendPO po2,
            UserCookiePO userCookiePO,Integer... types) {
        UserCondition userCondition = new UserCondition();
    	if(types!=null && types.length>0) {
    		String str = "";
    		for(Integer type:types) {
    			if(str.length()>0) {
    				str +=",";
    			}
    			str += type.intValue();
    		}
    		if(str.length()>0) {
    			userCondition.setManageType(str);
    		}
    	}
    	if(po!=null) {
            userCondition.setId(po.getId());
            userCondition.setLoginName(po.getLoginName());
            userCondition.setPassword(po.getPassword());
            userCondition.setMobile(po.getMobile());
            userCondition.setMail(po.getMail());
            userCondition.setQrcode(po.getQrcode());
            userCondition.setSalt(po.getSalt());
            userCondition.setUpdateSalt(po.getUpdateSalt());
            userCondition.setCompanyId(po.getCompanyId());
            userCondition.setBeginTime(po.getBeginTime());
            userCondition.setFinishTime(po.getFinishTime());
    	}

    	if(po2!=null) {
            userCondition.setUxId(po2.getId());
            userCondition.setSex(po2.getSex());
            userCondition.setNickname(po2.getNickname());
            userCondition.setHeadPicUrl(po2.getHeadPicUrl());
            userCondition.setName(po2.getName());
            userCondition.setIsDeleted(po2.getIsDeleted());
            userCondition.setIsAdministrator(po2.getIsAdministrator());
    	}
        int cnt = userReadDAO.countOfPageUser(userCondition);
        List<UserCondition> list = new ArrayList<UserCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = userReadDAO.findOfPageUser(userCondition, page);
        }
        PageResult<UserCondition> pageResult = new PageResult<UserCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    @Override
    public PageResult<UserCondition> findPageUser(Pagination page,String sysCode,Long rid, UserPO po, Integer... types) {

        UserCondition userCondition = new UserCondition();

    	if(types!=null && types.length>0) {
    		if(types.length==1) {
    			userCondition.setType(types[0]);
    			userCondition.setManageType("");
    		}else {
        		String str = "";
        		for(Integer type:types) {
        			if(str.length()>0) {
        				str +=",";
        			}
        			str += type.intValue();
        		}
        		if(str.length()>0) {
        			userCondition.setManageType(str);
        		}
    		}
    	}
    	if(sysCode!=null) {
    		userCondition.setSysCode(sysCode);
    	}
    	if(rid!=null) {
    		userCondition.setRoleId(rid);
    	}
    	if(po!=null) {
            userCondition.setId(po.getId());
            userCondition.setLoginName(po.getLoginName());
            userCondition.setPassword(po.getPassword());
            userCondition.setMobile(po.getMobile());
            userCondition.setMail(po.getMail());
            userCondition.setQrcode(po.getQrcode());
            userCondition.setSalt(po.getSalt());
            userCondition.setUpdateSalt(po.getUpdateSalt());
            userCondition.setIsAvailable(po.getIsAvailable());
            userCondition.setCompanyId(po.getCompanyId());
            userCondition.setEnterpriseId(po.getEnterpriseId());
            userCondition.setBeginTime(po.getBeginTime());
            userCondition.setFinishTime(po.getFinishTime());
    	}
        int cnt = userReadDAO.countOfPageUserMore(userCondition);
        List<UserCondition> list = new ArrayList<UserCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = userReadDAO.findOfPageUserMore(userCondition, page);
        }
        PageResult<UserCondition> pageResult = new PageResult<UserCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }
    @Override
    public PageResult<UserCondition> findPageUser(Pagination page, UserPO po, Integer... types) {
        UserCondition userCondition = new UserCondition();

    	if(types!=null && types.length>0) {
    		if(types.length==1) {
    			userCondition.setType(types[0]);
    			userCondition.setManageType("");
    		}else {
        		String str = "";
        		for(Integer type:types) {
        			if(str.length()>0) {
        				str +=",";
        			}
        			str += type.intValue();
        		}
        		if(str.length()>0) {
        			userCondition.setManageType(str);
        		}
    		}
    	}
    	if(po!=null) {
            userCondition.setId(po.getId());
            userCondition.setLoginName(po.getLoginName());
            userCondition.setPassword(po.getPassword());
            userCondition.setMobile(po.getMobile());
            userCondition.setMail(po.getMail());
            userCondition.setQrcode(po.getQrcode());
            userCondition.setSalt(po.getSalt());
            userCondition.setUpdateSalt(po.getUpdateSalt());
            userCondition.setIsAvailable(po.getIsAvailable());
            userCondition.setCompanyId(po.getCompanyId());
            userCondition.setEnterpriseId(po.getEnterpriseId());
            userCondition.setBeginTime(po.getBeginTime());
            userCondition.setFinishTime(po.getFinishTime());
    	}
        int cnt = userReadDAO.countOfPageUser(userCondition);
        List<UserCondition> list = new ArrayList<UserCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = userReadDAO.findOfPageUser(userCondition, page);
        }
        PageResult<UserCondition> pageResult = new PageResult<UserCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
    @Override
    public List<UserPO> userByloginName(UserPO po) {
        UserPO userPO = new UserPO();
        userPO.setLoginName(po.getLoginName());
        List<UserPO> list = userReadDAO.findAll(userPO,null);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public UserPO userById(UserPO po) {

        return userReadDAO.findById(po);
    }

	@Override
	public UserCondition userByMail(String mail) {
		UserPO userPO = new UserPO();
		//查询在职的会员信息
		userPO.setMail(mail);
        List<UserCondition> list = userReadDAO.findUniqueUser(userPO,null);

		if (list.size() == 0) {
        	if(StringUtils.isNotEmpty(mail)){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_EXIST, "邮箱未认证");
        	}
        }
        UserCondition user = null;
        // 保证同一邮箱有效是否多个
        boolean whetherAbnormal = false;
        for (UserCondition userCondition : list) {
        	//用户没有失效、并且用户处于在职状态
			if(userCondition.getAccountStatus() == 0){
				if(StringUtils.isNotEmpty(user) && whetherAbnormal){
					throw new BusinessException(BusinessExceptionConstant.USER_NO_UNIQUE, "账户异常，请联系客服解决");
				}
				user = userCondition;
				whetherAbnormal = true;
			}
		}
        if(StringUtils.isEmpty(user)){
        	throw new BusinessException(BusinessExceptionConstant.ACCOUNT_EXPIRED,"账户已失效");
        }
        return user;
	}

	@Override
	public List<UserPO> userByManage(String mobile) {
		UserPO po = new UserPO();
        po.setMobile(mobile);
		return userReadDAO.findAll(po,null);
	}
	/**
	 * 通过用户id查询用户昵称和头像信息
	 * @return
	 */
	@Override
	public UserCondition userWelfareAppById(Long userId) {

		return userReadDAO.userWelfareAppById(userId);
	}

	@Override
	public PageResult<UserExtendPO> queryUserPageByCompanyId(Long companyId, Pagination page) {
		List<UserExtendPO> pos=uerDAO.queryUserPageByCompanyId(companyId,page);
		PageResult<UserExtendPO> result=new PageResult<>();
		result.setList(pos);
		result.setPageNo(page.getPageNo());
		result.setPageSize(page.getPageSize());
		Integer count=uerDAO.queryUserPageTotalCountByCompanyId(companyId);
		result.setTotalSize(count);
		return result;
	}

	@Override
	public PageResult<UserExtendPO> queryUserPageByDepartmentId(Long departmentId, Pagination page) {
		List<UserExtendPO> pos=uerDAO.queryUserPageByDepartmentId(departmentId,page);
		PageResult<UserExtendPO> result=new PageResult<>();
		result.setList(pos);
		result.setPageNo(page.getPageNo());
		result.setPageSize(page.getPageSize());
		Integer count=uerDAO.queryUserPageTotalCountByDepartmentId(departmentId);
		result.setTotalSize(count);
		return result;
	}

	@Override
	public List<UserPO> queryUserByIds(List<Long> userIdList) {
		return userReadDAO.queryUserByIds(userIdList);
	}
	/**
	 * 根据用户手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	@Override
	public List<UserPO> findByMobileAndRegister(String mobile, Long platformId) {
		// 根据手机号查询会员列表、根据注册时间排序
        return userReadDAO.findByMobileAndRegister(mobile,platformId);
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param mobile
	 * @return
	 */
	@Override
	public UserPO findLatestLoginByMobile(String mobile, Long platformId) {
        return userReadDAO.findLatestLoginByMobile(mobile,platformId);
	}

	@Override
	public List<UserPO> findUsersByCompanyId(Long companyId) {
		return userReadDAO.findUsersByCompanyId(companyId);
	}

	@Override
	public List<UserPO> findAll(UserPO po) {
		return userReadDAO.findAll(po,null);
	}

	@Override
	public List<UserPO> queryUsersByEmail(String email) {
		return userReadDAO.queryUsersByEmail(email);
	}
	/**
	 * 根据邮箱查询用户信息
	 */
	@Override
	public UserCondition findByMail(String mail) {
		List<UserCondition> byMail = userReadDAO.findByMail(mail);
		/*if(StringUtils.isNotEmpty(byMail)){
			if(byMail.size()>1){
				throw new BusinessException(mail + "用户在user表中出现多条数据");
			}
		}
		return byMail.get(0);*/
		if(StringUtils.isNotEmpty(byMail)){
			if(byMail.size()>1){
				throw new BusinessException(mail + "用户在user表中出现多条数据");
			} else {
				return byMail.get(0);
			}
		}
		return null;
	}
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	@Override
	public UserPO findByUserIdMobile(Long userId, String mobile,Long platformId) {
		List<UserPO> list = userReadDAO.findByMobile(mobile,platformId);
		for (UserPO userPO : list) {
			if(userPO.getId().equals(userId)){
				return userPO;
			}
		}
		return null;
	}
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserPO findByWeiXinOpenId(String openId, Long platformId) {
		List<UserPO> userList = userReadDAO.findByWeiXinOpenId(openId, platformId);
		if(StringUtils.isNotEmpty(userList)){
			return userList.get(0);
		}
		return null;
	}

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserPO findLatestLoginByWeiXinOpenId(String openId, Long platformId) {
		List<UserPO> userList = userReadDAO.findLatestLoginByWeiXinOpenId(openId, platformId);
		if(StringUtils.isNotEmpty(userList)){
			return userList.get(0);
		}
		return null;
	}

	/**
	 * 根据手机号平台id查询用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	@Override
	public List<UserPO> findListByManage(String mobile, Long platformId) {
		// 根据手机号查询会员列表
		return userReadDAO.findByMobile(mobile, platformId);
	}
	/**
	 * 根据导入批次id查询用户信息
	 */
	@Override
	public List<UserPO> findByImportId(Long importId) {
		// TODO Auto-generated method stub
		return userReadDAO.findByImportId(importId);
	}

	@Override
	public List<Long> findUserIdsByCompanyId(Long companyId, Integer isAdministrator) {
		return userReadDAO.findUserIdsByCompanyId(companyId, isAdministrator);
	}

	@Override
	public UserPO findAdminUserByManage(String mobile, Long platformId) {
		List<UserPO> userList = userReadDAO.findAdminUserByManage(mobile, platformId);
		for (UserPO userPO : userList) {
			if(userPO.getIsAdministrator() == 1){
				return userPO;
			}
		}
		return null;
	}

	@Override
	public Boolean findIsExistUser(String mobile, Long companyId, Long platformId) {
		if(StringUtils.isEmpty(mobile)){
			throw new BusinessException("手机号不能为空");
		}
		if(StringUtils.isEmpty(companyId)){
			throw new BusinessException("公司不能为空");
		}
		if(StringUtils.isEmpty(platformId)){
			throw new BusinessException("平台不能为空");
		}
		boolean isTrue = true;
		if(!companyId.equals(2L)){
			isTrue = false;
		}
		int i = userReadDAO.findIsExistUser(mobile, isTrue, platformId);
		if(i == 0){
			return false;
		}
		return true;
	}

	@Override
	public List<UserPO> findUserByMobile(String mobile, Long platformId) {
		// 根据手机号查询会员列表、根据注册时间排序
		return userReadDAO.findUserByMobile(mobile,platformId);
	}

	public UserPO findByMailAndCompany(String mail, String companyName) {
		return userReadDAO.findByMailAndCompany(mail, companyName);
	}

	@Override
	public List<UserPO> findByAccountAndMobile(String account, String mobile) {
		// TODO Auto-generated method stub
		return userReadDAO.findByAccountAndMobile(account, mobile);
	}
	@Override
	public PageResult<UserPO> findManagePage(Pagination page, UserPO po) {
		// TODO Auto-generated method stub

        int cnt = userReadDAO.countManage(po,null);
        List<UserPO> list = new ArrayList<UserPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = userReadDAO.findManagePage(po, page);
        }
        PageResult<UserPO> pageResult = new PageResult<UserPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}

	/**
	 * 根据渠道用户唯一标识查询用户信息
	 * @param channelUserUnique
	 * @return
	 */
	@Override
	public List<UserPO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId, Long platformId) {
		// 根据手机号查询会员列表、根据注册时间排序
		return userReadDAO.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param channelUserUnique
	 * @return
	 */
	@Override
	public UserPO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId, Long platformId) {
		return userReadDAO.findLatestLoginByChannelUserUnique(channelUserUnique,companyId,platformId);
	}
}
