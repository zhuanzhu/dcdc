package com.egeo.components.user.service.read.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.converter.UserCookieConverter;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.dao.write.EnterpriseMapper;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserWelfareApp;
import com.egeo.components.user.manage.read.CompanyReadManage;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("userReadService")
public class UserReadServiceImpl implements UserReadService {
	@Autowired
	private UserReadManage userReadManage;
	@Autowired
	private UserExtendReadManage userExtendReadManage;
	@Autowired
	private RoleReadService roleReadService;
	@Autowired
	private CompanyReadManage companyReadManage;
	@Autowired
	private EnterpriseMapper enterpriseReadManage;
	@Override
	public UserDTO findUniqueUser(UserDTO dto) {

		UserPO po = userReadManage.findUniqueUser(UserConverter.toPO(dto));
		UserDTO rslt = UserConverter.toDTO(po);
		if(po.getCompanyId()!=null ) {
			CompanyPO companypo = new CompanyPO();
			companypo.setId(po.getCompanyId());
			CompanyPO company = companyReadManage.findCompanyById(companypo);
			if(company.getEnterpriseId()!=null && company.getEnterpriseId()>0) {
				rslt.setEnterpriseId(company.getEnterpriseId());
			}
			if(company.getCompanyType()!=null && company.getCompanyType()>=0) {
				rslt.setCompanyType(company.getCompanyType());
			}
		}
		return rslt;
	}

	@Override
	public UserDTO findUserByID(Long userId) {

		UserPO po = userReadManage.findUserByID(userId);
		UserExtendPO byId = userExtendReadManage.findById(userId);
		if (StringUtils.isNotEmpty(po)&&StringUtils.isNotEmpty(byId)) {
			po.setName(byId.getName());
			UserDTO rslt = UserConverter.toDTO(po);
			rslt.setChannelSource(byId.getChannelSource());
			rslt.setIdCardNo(byId.getIdCardNo());
			if(po.getCompanyId()!=null) {
				CompanyPO companypo = new CompanyPO();
				companypo.setId(po.getCompanyId());
				CompanyPO company = companyReadManage.findCompanyById(companypo);
				if(company.getEnterpriseId()!=null && company.getEnterpriseId()>0) {
					rslt.setEnterpriseId(company.getEnterpriseId());
				}
			}
			return rslt;
		} else {
			return null;
		}

	}

	@Override
	public PageResult<UserDTO> findPage(Pagination page, UserDTO dto, UserExtendDTO userExtendDTO,
			UserCookieDTO userCookieDTO,Integer... types) {
		UserPO po = UserConverter.toPO(dto);
		UserExtendPO po2 = UserExtendConverter.toPO(userExtendDTO);
		UserCookiePO userCookiePO = UserCookieConverter.toPO(userCookieDTO);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (po.getBeginTime() != null) {
				Date beginTime = format.parse(po.getBeginTime().replace("Z", " UTC"));
				String format2 = sdf2.format(beginTime);
				po.setBeginTime(format2.substring(0, 10));
			}
			if (po.getFinishTime() != null) {
				Date finishTime = format.parse(po.getFinishTime().replace("Z", " UTC"));
				String string = sdf2.format(finishTime);
				po.setFinishTime(string.substring(0, 10));
			}
		} catch (ParseException e) {
			throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
		}

		PageResult<UserCondition> pageResult = userReadManage.findPageUser(page, po, po2, userCookiePO,types);

		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserCondition tmp : pageResult.getList()) {
			UserDTO userDTO = UserConverter.toDTO(tmp);
			userDTO.setName(tmp.getName());
			list.add(userDTO);
		}
		PageResult<UserDTO> result = new PageResult<UserDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;

	}

	@Override
	public PageResult<UserDTO> findPage(Pagination page, UserDTO dto, Integer... types) {
		UserPO po = UserConverter.toPO(dto);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (po.getBeginTime() != null) {
				Date beginTime = format.parse(po.getBeginTime().replace("Z", " UTC"));
				String format2 = sdf2.format(beginTime);
				po.setBeginTime(format2.substring(0, 10));
			}
			if (po.getFinishTime() != null) {
				Date finishTime = format.parse(po.getFinishTime().replace("Z", " UTC"));
				String string = sdf2.format(finishTime);
				po.setFinishTime(string.substring(0, 10));
			}
		} catch (ParseException e) {
			throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
		}

		PageResult<UserCondition> pageResult = userReadManage.findPageUser(page, po,types);

		List<UserDTO> list = new ArrayList<UserDTO>();
		if(pageResult!=null && pageResult.getList()!=null && pageResult.getList().size()>0) {
			List<CompanyPO> companys = companyReadManage.findCompanyAll();
			List<Enterprise> enterprises = enterpriseReadManage.findAllEnterprises();
			for (UserCondition tmp : pageResult.getList()) {
				UserDTO userDTO = UserConverter.toDTO(tmp);
				userDTO.setName(tmp.getName());
				if(userDTO.getEnterpriseId()!=null) {
					for(Enterprise one :enterprises) {
						if(one.getId().longValue()==userDTO.getEnterpriseId().longValue()) {
							userDTO.setEnterpriseName(one.getName());
						}
					}
				}
				if(userDTO.getCompanyId()!=null) {
					for(CompanyPO one :companys) {
						if(one.getId().longValue()==userDTO.getCompanyId().longValue()) {
							userDTO.setCompanyName(one.getCompanyName());
						}
					}
				}
				List<RoleDTO> userRoles = roleReadService.roleListByUserId(userDTO.getId());
				userDTO.setRoles(userRoles);
				list.add(userDTO);
			}
		}
		PageResult<UserDTO> result = new PageResult<UserDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;

	}
	@Override
	public UserDTO userById(UserDTO dto) {
		UserPO userPO = userReadManage.userById(UserConverter.toPO(dto));
		return UserConverter.toDTO(userPO);
	}

	@Override
	public PageResult<UserDTO> findProductUser(Pagination page, UserDTO dto) {
		UserPO po = UserConverter.toPO(dto);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (po.getBeginTime() != null) {
				Date beginTime = format.parse(po.getBeginTime().replace("Z", " UTC"));
				String format2 = sdf2.format(beginTime);
				po.setBeginTime(format2.substring(0, 10));
			}
			if (po.getFinishTime() != null) {
				Date finishTime = format.parse(po.getFinishTime().replace("Z", " UTC"));
				String string = sdf2.format(finishTime);
				po.setFinishTime(string.substring(0, 10));
			}
		} catch (ParseException e) {
			throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
		}

		PageResult<UserPO> pageResult = userReadManage.findPage(page, po);

		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserPO tmp : pageResult.getList()) {
			UserDTO UserDTO = UserConverter.toDTO(tmp);
			list.add(UserDTO);
		}
		PageResult<UserDTO> result = new PageResult<UserDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	/**
	 * 通过用户id查询用户昵称和头像信息
	 *
	 * @return
	 */
	@Override
	public UserWelfareApp userWelfareAppById(Long userId) {
		UserWelfareApp userWelfareApp = new UserWelfareApp();
		UserCondition userCondition = userReadManage.userWelfareAppById(userId);
		userWelfareApp.setId(userCondition.getId());
		userWelfareApp.setNickname(userCondition.getNickname());
		userWelfareApp.setHeadPicUrl(userCondition.getHeadPicUrl());
		userWelfareApp.setMobile(userCondition.getMobile());
		return userWelfareApp;
	}

	/**
	 * 根据邮箱查询用户
	 *
	 * @param mail
	 * @return
	 */
	@Override
	public UserDTO userByMail(String mail) {
		UserCondition userCondition = userReadManage.userByMail(mail);
		if (StringUtils.isNotEmpty(userCondition)) {
			UserDTO userDTO = UserConverter.toDTO(userCondition);
			userDTO.setRegtime(userCondition.getRegtime());
			userDTO.setStatus(userCondition.getStatus());
			return userDTO;
		} else {
			return null;
		}

	}

	@Override
	public PageResult<UserExtendDTO> queryUserListByCompanyId(Long companyId, Pagination page) {
		PageResult<UserExtendPO> pageResult = userReadManage.queryUserPageByCompanyId(companyId, page);
		PageResult<UserExtendDTO> result = new PageResult<>();
		List<UserExtendPO> pos = pageResult.getList();
		List<UserExtendDTO> dtos = UserExtendConverter.toDTO(pos);
		result.setList(dtos);
		result.copy(pageResult);
		return result;
	}

	@Override
	public PageResult<UserExtendDTO> queryUserListByDepartmentId(Long departmentId, Pagination page) {
		PageResult<UserExtendPO> pageResult = userReadManage.queryUserPageByDepartmentId(departmentId, page);
		PageResult<UserExtendDTO> result = new PageResult<>();
		List<UserExtendPO> pos = pageResult.getList();
		List<UserExtendDTO> dtos = UserExtendConverter.toDTO(pos);
		result.setList(dtos);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<UserDTO> queryUserByIds(List<Long> userIdList) {
		return UserConverter.toDTO(userReadManage.queryUserByIds(userIdList));
	}

	/**
	 * 根据用户手机号查询用户信息
	 *
	 * @param mobile
	 * @return
	 */
	@Override
	public List<UserDTO> findByMobileAndRegister(String mobile,Long platformId) {
		List<UserPO> userPO = userReadManage.findByMobileAndRegister(mobile,platformId);
		List<UserDTO> userDtos = UserConverter.toDTO(userPO);
		for(UserDTO userDto : userDtos) {
			if(userDto.getCompanyId()!=null ) {
				CompanyPO companypo = new CompanyPO();
				companypo.setId(userDto.getCompanyId());
				CompanyPO company = companyReadManage.findCompanyById(companypo);
				if(company.getEnterpriseId()!=null && company.getEnterpriseId()>0) {
					userDto.setEnterpriseId(company.getEnterpriseId());
				}
				if(company.getCompanyType()!=null && company.getCompanyType()>=0) {
					userDto.setCompanyType(company.getCompanyType());
				}
			}
		}

		return userDtos;
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param mobile
	 * @return
	 */
	@Override
	public UserDTO findLatestLoginByMobile(String mobile,Long platformId) {
		UserPO userPO = userReadManage.findLatestLoginByMobile(mobile,platformId);
		return UserConverter.toDTO(userPO);
	}

	@Override
	public List<UserDTO> findUsersByCompanyId(Long companyId) {
		List<UserPO> userDTOList = userReadManage.findUsersByCompanyId(companyId);
		return UserConverter.toDTO(userDTOList);
	}

	@Override
	public List<UserDTO> findUser(UserDTO userDTO) {
		List<UserPO> userDTOList = userReadManage.findAll(UserConverter.toPO(userDTO));
		return UserConverter.toDTO(userDTOList);
	}
	@Override
	public PageResult<UserDTO> findManagePage(Pagination page, UserDTO dto){

		UserPO po = UserConverter.toPO(dto);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (po.getBeginTime() != null) {
				Date beginTime = format.parse(po.getBeginTime().replace("Z", " UTC"));
				String format2 = sdf2.format(beginTime);
				po.setBeginTime(format2.substring(0, 10));
			}
			if (po.getFinishTime() != null) {
				Date finishTime = format.parse(po.getFinishTime().replace("Z", " UTC"));
				String string = sdf2.format(finishTime);
				po.setFinishTime(string.substring(0, 10));
			}
		} catch (ParseException e) {
			throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
		}

		PageResult<UserPO> pageResult = userReadManage.findManagePage(page, po);

		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserPO tmp : pageResult.getList()) {
			UserDTO UserDTO = UserConverter.toDTO(tmp);
			list.add(UserDTO);
		}
		PageResult<UserDTO> result = new PageResult<UserDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;

	}

	@Override
	public List<UserDTO> queryUsersByEmail(String email) {
		return UserConverter.toDTO(userReadManage.queryUsersByEmail(email));
	}
	/**
	 * 根据邮箱查询用户信息
	 */
	@Override
	public UserDTO findByMail(String mail) {
		UserCondition userCondition = userReadManage.findByMail(mail);
		if (StringUtils.isNotEmpty(userCondition)) {
			UserDTO userDTO = UserConverter.toDTO(userCondition);
			userDTO.setRegtime(userCondition.getRegtime());
			userDTO.setStatus(userCondition.getStatus());
			userDTO.setName(userCondition.getName());
			return userDTO;
		} else {
			return null;
		}
	}
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	@Override
	public UserDTO findByUserIdMobile(Long userId, String mobile,Long platformId) {
		UserPO userPO = userReadManage.findByUserIdMobile(userId, mobile,platformId);
		return UserConverter.toDTO(userPO);
	}
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserDTO findByWeiXinOpenId(String openId, Long platformId) {
		UserPO userPO = userReadManage.findByWeiXinOpenId(openId, platformId);
		return UserConverter.toDTO(userPO);
	}

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserDTO findLatestLoginByWeiXinOpenId(String openId, Long platformId) {
		UserPO userPO = userReadManage.findLatestLoginByWeiXinOpenId(openId, platformId);
		return UserConverter.toDTO(userPO);
	}
	/**
	 * 根据手机号平台id查询用户信息
	 */
	@Override
	public List<UserDTO> findListByManage(String mobile, Long platformId) {
		List<UserPO> userList = userReadManage.findListByManage(mobile, platformId);
		return UserConverter.toDTO(userList);
	}
	/**
	 * 根据导入批次id查询用户信息
	 */
	@Override
	public List<UserDTO> findByImportId(Long importId) {
		List<UserPO> list = userReadManage.findByImportId(importId);
		return UserConverter.toDTO(list);
	}

	@Override
	public List<Long> findUserIdsByCompanyId(Long companyId, Integer isAdministrator) {
		if(StringUtils.isEmpty(companyId))
			throw new BusinessException("公司编号不能为空");
		if(StringUtils.isEmpty(isAdministrator))
			throw new BusinessException("是否是管理员用户不能为空");
		return userReadManage.findUserIdsByCompanyId(companyId, isAdministrator);
	}

	@Override
	public UserDTO findAdminUserByManage(String mobile, Long platformId) {
		UserPO userPO = userReadManage.findAdminUserByManage(mobile, platformId);
		return UserConverter.toDTO(userPO);
	}

	@Override
	public Boolean findIsExistUser(String mobile, Long companyId, Long platformId) {
		return userReadManage.findIsExistUser(mobile, companyId, platformId);
	}

	@Override
	public List<UserDTO> findUserByMobile(String mobile, Long platformId) {
		List<UserPO> userPO = userReadManage.findUserByMobile(mobile,platformId);
		return UserConverter.toDTO(userPO);
	}

	public UserDTO findByMailAndCompany(String mail, String companyName) {
		return UserConverter.toDTO(userReadManage.findByMailAndCompany(mail, companyName));
	}

	public List<UserDTO> findByAccountAndMobile(String account, String mobile) {
		return UserConverter.toDTO(userReadManage.findByAccountAndMobile(account, mobile));
	}

	@Override
	public UserExtendCondition findCompanyAdmin(Long companyId) {
		// TODO Auto-generated method stub
		return userExtendReadManage.findCompanyAdmin(companyId);
	}

	/**
	 * 根据渠道用户唯一标识查询用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	@Override
	public List<UserDTO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId,Long platformId) {
		List<UserPO> userPO = userReadManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
		List<UserDTO> userDtos = UserConverter.toDTO(userPO);
		for(UserDTO userDto : userDtos) {
			if(userDto.getCompanyId()!=null ) {
				CompanyPO companypo = new CompanyPO();
				companypo.setId(userDto.getCompanyId());
				CompanyPO company = companyReadManage.findCompanyById(companypo);
				if(company.getEnterpriseId()!=null && company.getEnterpriseId()>0) {
					userDto.setEnterpriseId(company.getEnterpriseId());
				}
				if(company.getCompanyType()!=null && company.getCompanyType()>=0) {
					userDto.setCompanyType(company.getCompanyType());
				}
			}
		}

		return userDtos;
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	@Override
	public UserDTO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId,Long platformId) {
		UserPO userPO = userReadManage.findLatestLoginByChannelUserUnique(channelUserUnique,companyId,platformId);
		return UserConverter.toDTO(userPO);
	}
}
