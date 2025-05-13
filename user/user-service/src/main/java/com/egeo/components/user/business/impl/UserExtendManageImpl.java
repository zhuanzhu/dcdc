package com.egeo.components.user.business.impl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.user.enums.UserChannelSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.business.UserExtendManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.converter.DepMemberConverter;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.facade.CompanyFacade;
import com.egeo.components.user.facade.UserCookieFacade;
import com.egeo.components.user.facade.UserExtendFacade;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.facade.UserRoleFacade;
import com.egeo.components.user.facade.UserWelfareFacade;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.vo.DepMemberVO;
import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.components.user.vo.UserWelfare;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("userExtend")
public class UserExtendManageImpl implements UserExtendManage{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="userExtendFacade")
	private UserExtendFacade userExtendFacade;

	@Resource(name="userFacade")
	private UserFacade userFacade;

	@Resource(name="userRoleFacade")
	private UserRoleFacade userRoleFacade;

	@Resource(name="userWelfareFacade")
	private UserWelfareFacade userWelfareFacade;

	@Resource(name="user")
	private UserManage userManage;

	@Resource(name="companyFacade")
	private CompanyFacade companyFacade;

	@Autowired
	private CompanyReadService companyReadService;
	@Override
	public UserExtendDTO findById(Long id) {

		return userExtendFacade.findById(id);
	}

	@Resource(name="userCookieFacade")
	private UserCookieFacade userCookieFacade;

	/**
     * 完善用户扩展表信息
     * @return
     */
	@Override
	public int saveUserExtend(UserExtendVO userExtendVO) {

		return userExtendFacade.saveUserExtendWithTx(UserExtendConverter.toDTO(userExtendVO));
	}
	@Override
	public Map<String, Object> ImproveUserInformation(Long userId, String name, Long departmentId, Integer sex,
			Long birthday,Long platformId) {
    	//添加扩展表信息
    	int rows = this.updateUserExtend(userId, name, sex,birthday,departmentId);
    	if(rows != 0){
    		return userByUserId(userId,null,platformId);
    	}else{

    		throw new BusinessException("完善用户信息失败");
    	}

	}

	/**
	 * 添加扩展表信息
	 */
	@Override
	public Long saveUserWelfareWithTx(Long userId, Long departmentId, Long entryTime) {
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		UserWelfareDTO userWelfareDTO = new UserWelfareDTO();
		userWelfareDTO.setUserId(userId);
		if(EmptyUtil.isNotEmpty(entryTime)){
			try {
				userWelfareDTO.setEntryTime(format.parse(format.format(entryTime)));
			} catch (ParseException e) {
				throw new BusinessException("用户入职时间格式转换错误");
			}
		}

		userWelfareDTO.setDepartmentId(departmentId);
		return userExtendFacade.saveUserWelfareWithTx(userWelfareDTO);
	}
	/**
     * 根据用户id修改用户扩展表信息
     * @param dto
     * @return
     */
	@Override
	public int updateUserExtend(Long userId, String name, Integer sex, Long birthday,Long departmentId) {
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    	UserExtendDTO userExtendDTO = new UserExtendDTO();
    	userExtendDTO.setId(userId);
    	userExtendDTO.setName(name);
    	userExtendDTO.setNamePy(StringUtils.Hanyu2Pinyin(name));
    	userExtendDTO.setSex(sex);
    	if(EmptyUtil.isNotEmpty(birthday)){
    		try {
    			userExtendDTO.setBirthday(format.parse(format.format(birthday)));
    		} catch (ParseException e) {
    			throw new BusinessException("用户生日格式转换错误");
    		}
    	}

    	if(EmptyUtil.isNotEmpty(departmentId)){
			UserWelfareDTO dto = new UserWelfareDTO();
			dto.setUserId(userId);
			dto.setDepartmentId(departmentId);
			userWelfareFacade.updateUserWelfareByUserIdWithTx(dto);
		}

		return userExtendFacade.updateUserExtend(userExtendDTO);
	}
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	@Override
	public Map<String, Object> userExtendByUserId(Long userId) {
		UserExtendDTO dto = userExtendFacade.userExtendByUserId(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", dto.getId());
		map.put("name", dto.getName());
		map.put("headPicUrl", dto.getHeadPicUrl());
		map.put("companyId", dto.getCompanyId());
		map.put("companyName", dto.getCompanyName());
		return map;
	}
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	@Override
	public Map<String, Object> userByUserId(Long userId,String weiXinOpenId,Long platformId) {
		UserExtendDTO dto = userExtendFacade.userByUserId(userId);
		dto.setPlatformId(platformId);
		Map<String, Object> map = mapAssignment(dto);
		// 根据手机号和微信OpenId是否为空进行判断是否绑定
		if(weiXinOpenId != null && dto.getMobile() != null){
			// 根据用户id绑定微信OpenId
			userExtendFacade.bindingWeiXinOpenIdByUserId(userId,weiXinOpenId);
		}else{
			map.put("weiXinOpenId", weiXinOpenId);
		}
		//拼接返回值
		return map;
	}
	/**
	 * 拼接返回值
	 * @param dto
	 * @return
	 */
	private Map<String, Object> mapAssignment(UserExtendDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", dto.getId());
		map.put("companyType", companyFacade.findCompanyTypeByUserId(dto.getId()));
		map.put("name", dto.getName());
		map.put("headPicUrl", dto.getHeadPicUrl());
		map.put("mobile", dto.getMobile());
		Integer isDlfUser = 0;
		if(EmptyUtil.isNotEmpty(dto.getChannelSource()) && Objects.equals(dto.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
			isDlfUser = 1;
		}
		map.put("isDlfUser", isDlfUser);
		map.put("channelSource", dto.getChannelSource());
		if(EmptyUtil.isNotEmpty(dto.getMobile())){
			// 根据手机号平台id查询用户信息
			List<UserDTO> userList = userManage.findListByManage(dto.getMobile(),dto.getPlatformId());
			map.put("userNum", userList.size());
		}else{
			map.put("userNum", 0);
		}
		map.put("departmentId", dto.getDepartmentId());
		map.put("departmentName", dto.getDepartmentName());
		map.put("sex", dto.getSex());
		map.put("birthday", dto.getBirthday());
		map.put("entryTime", dto.getEntryTime());
		map.put("mail", dto.getMail());

		map.put("isAvailable", dto.getIsAvailable());
		if(dto.getIsAvailable() == 1){
			map.put("companyId", dto.getCompanyId());
			map.put("companyName", dto.getCompanyName());
			map.put("backgrondImg", dto.getBackgrondImg());
			map.put("companyLogo", dto.getCompanyLogo());
			//根据公司id查询公司页面配置信息
			List<CompanyPageDTO> companyPageDTOList = userExtendFacade.findCompanyPageByCompanyId(dto.getCompanyId());
			for (CompanyPageDTO companyPageDTO : companyPageDTOList) {
				//类型：1、我的订单 2、保险订单 3、我的体检
				switch (companyPageDTO.getType()) {
				case 1:
					if(companyPageDTO.getShowOrNot() == 0){
						map.put("showOrder", false);
					} else if(companyPageDTO.getShowOrNot() == 1){
						map.put("showOrder", true);
					}
					break;

				case 2:
					if(companyPageDTO.getShowOrNot() == 0){
						map.put("showInsurance", false);
					} else if(companyPageDTO.getShowOrNot() == 1){
						map.put("showInsurance", true);
					}
					break;

				case 3:
					if(companyPageDTO.getShowOrNot() == 0){
						map.put("showPhysicalExam", false);
					} else if(companyPageDTO.getShowOrNot() == 1){
						map.put("showPhysicalExam", true);
					}
					break;

				default:
					throw new BusinessException("公司页面配置类型未指定");
				}
			}
		}else{
			map.put("companyId", null);
			map.put("companyName", null);
			map.put("backgrondImg", null);
			map.put("companyLogo", null);
			//离职默认显示
			map.put("showOrder", true);
			map.put("showInsurance", true);
			map.put("showPhysicalExam", true);
		}

		//showInsurance、showPhysicalExam此版本默认为false

		if(EmptyUtil.isEmpty(dto.getBirthday())){
			map.put("isNullBirthday", 0);
		}else{
			map.put("isNullBirthday", 1);
		}
		if(EmptyUtil.isEmpty(dto.getPaymentCode())){
			map.put("isPaymentCode", 0);
		}else{
			map.put("isPaymentCode", 1);
		}
		if(EmptyUtil.isEmpty(dto.getName())
		|| EmptyUtil.isEmpty(dto.getDepartmentId())
		|| EmptyUtil.isEmpty(dto.getSex())
		|| EmptyUtil.isEmpty(dto.getBirthday())){
			map.put("isNotPerfect", 0);
		}else{
			map.put("isNotPerfect", 1);
		}
		return map;
	}
	/**
	 * 根据公司id按条件查询所有用户信息
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> userAllOfPage(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime,Long channelId,String campaignCode,
			Long registerStoreId,Long platformId,Pagination page) {
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		UserExtendDTO dto = new UserExtendDTO();
		//set数据
		dto.setCompanyId(companyId);
		dto.setName(name);
		dto.setDepartmentId(departmentId);
		dto.setSex(sex);
		dto.setChannelId(channelId);
		dto.setCampaignCode(campaignCode);
		dto.setRegisterStoreId(registerStoreId);
		dto.setPlatformId(platformId);
		try {
			if(EmptyUtil.isNotEmpty(birthdayStartTime)){
				dto.setBirthdayStartTime(format.parse(format.format(birthdayStartTime)));
			}
			if(EmptyUtil.isNotEmpty(birthdayFinishTime)){
				dto.setBirthdayFinishTime(format.parse(format.format(birthdayFinishTime)));
			}
			if(EmptyUtil.isNotEmpty(entryStartTime)){
				dto.setEntryStartTime(format.parse(format.format(entryStartTime)));
			}
			if(EmptyUtil.isNotEmpty(entryFinishTime)){
				dto.setEntryFinishTime(format.parse(format.format(entryFinishTime)));
			}
		} catch (ParseException e) {
			throw new BusinessException("生日或入职时间搜索条件格式转换错误");
		}
		PageResult<UserExtendDTO> pageResult = userExtendFacade.userAllOfPage(dto,page);
		PageResult<Map<String, Object>> result = new PageResult<>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		List<UserExtendDTO> userExtendList = pageResult.getList();
		for (UserExtendDTO userExtendDTO : userExtendList) {
			Map<String, Object> map = new HashMap<>();
			map.put("userId", userExtendDTO.getId());
			map.put("name", userExtendDTO.getName());
			map.put("sex", userExtendDTO.getSex());
			map.put("birthday", userExtendDTO.getBirthday());
			map.put("entryTime", userExtendDTO.getEntryTime());
			map.put("mail", userExtendDTO.getMail());
			map.put("mobile", userExtendDTO.getMobile());
			map.put("departmentName", userExtendDTO.getDepartmentName());
			list.add(map);
		}
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public PageResult<Map<String, Object>> userExtendAllOfPage(Long loginId,Long platformId,Long companyId, Integer isAvailable,
			UserExtendDTO userExtendDTO, Long couponId, Long couponGroupId, Pagination page,Long userCompanyId) {
		//封装数据
		userExtendDTO.setIsAvailable(isAvailable);
		// 如果是否是管理员为空的话默认赋值为员工
		/*if(EmptyUtil.isEmpty(userExtendDTO.getIsAdministrator())){
			userExtendDTO.setIsAdministrator(0);
		}*/
		PageResult<UserExtendDTO> pageResult = new PageResult<UserExtendDTO>();;
		if (userCompanyId != null) {
			CompanyDTO companyById = companyFacade.findCompanyById(userCompanyId);
			Integer isManagementCompany = companyById.getIsManagementCompany();
			if (isManagementCompany == null) {
				userExtendDTO.setCompanyId(userCompanyId);
			}else {
				if ((platformId == 7L && isManagementCompany != 1L) || (platformId == 2L && isManagementCompany != 2L)) {
					userExtendDTO.setCompanyId(userCompanyId);
				}else {
					userExtendDTO.setCompanyId(companyId);
				}
			}

		}else {
			userExtendDTO.setCompanyId(companyId);
		}
		if(userExtendDTO.getCompanyId()!=null) {
			//优先选择了公司的查询
			pageResult =userExtendFacade.userExtendAllOfPage(userExtendDTO, couponId, couponGroupId, page);
		}else {
			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
				if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
					companyId = RuntimeContext.cacheUser().getCompanyId();
					userExtendDTO.setCompanyId(companyId);
					pageResult =userExtendFacade.userExtendAllOfPage(userExtendDTO, couponId, couponGroupId, page);
				}else if(RuntimeContext.cacheUser().getType().intValue()==2) {
					//获取所有的公司信息
					List<Long> companyIds = new ArrayList<Long>();
					CompanyDTO dto = new CompanyDTO();
					dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
					List<CompanyDTO> companys = companyReadService.findCompanyAll(dto);
					if(companys!=null) {
						for(CompanyDTO one : companys) {
							companyIds.add(one.getId());
						}
					}
					if(companyIds.size()==0) {
						companyIds.add(-99l);
					}
					pageResult =userExtendFacade.userExtendAllOfPage(userExtendDTO, couponId, couponGroupId, page,companyIds);
				}else if(RuntimeContext.cacheUser().getType().intValue()==1) {
					pageResult =userExtendFacade.userExtendAllOfPage(userExtendDTO, couponId, couponGroupId, page,null);
				}
			}else {
				return new PageResult<>();
			}
		}

		PageResult<Map<String, Object>> result = new PageResult<>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<UserExtendDTO> userExtendList = pageResult.getList();

		for (UserExtendDTO userExtendDTO2 : userExtendList) {

			Map<String, Object> map = new HashMap<>();
			map.put("userId", userExtendDTO2.getId());
			map.put("name", userExtendDTO2.getName());
			map.put("isDeleted", userExtendDTO2.getIsDeleted());
			map.put("accountStatus", userExtendDTO2.getAccountStatus());
			map.put("sex", userExtendDTO2.getSex());
			map.put("mail", userExtendDTO2.getMail());
			map.put("mobile", userExtendDTO2.getMobile());
			map.put("memberCode", userExtendDTO2.getMemberCode());
			map.put("birthday", userExtendDTO2.getBirthday());
			map.put("companyName", userExtendDTO2.getCompanyName());
			map.put("departmentName", userExtendDTO2.getDepartmentName());
			map.put("status", userExtendDTO2.getStatus());
			map.put("isavailable", userExtendDTO2.getIsAvailable());
			map.put("quitTime", userExtendDTO2.getQuitTime());
			map.put("invalidTime", userExtendDTO2.getInvalidTime());
			map.put("campaignCode", userExtendDTO2.getCampaignCode()); // 活动短码
			map.put("registerShopCode", userExtendDTO2.getRegisterStoreId()); // 注册门店码
			map.put("regTime",userExtendDTO2.getRegtime());
			if(EmptyUtil.isNotEmpty(userExtendDTO2.getChannelId())){
				//根据渠道id查询渠道信息
				ChannelDTO channelDTO = userExtendFacade.findChannelByChannelId(userExtendDTO2.getChannelId());
				if(EmptyUtil.isEmpty(channelDTO)){
					throw new BusinessException("渠道编号：" + userExtendDTO2.getChannelId() + "异常");
				}
				map.put("channelShortCode", channelDTO.getShortCode());
			}else{
				map.put("channelShortCode", null);
			}

			//查询当前登录人是不是后台账号管理员
			int loginIsBAM = 0;
			UserRoleDTO loginUserRoleDTO = new UserRoleDTO();
			loginUserRoleDTO.setUserId(loginId);
			long roleId = 0L;
			if (platformId==2) {
				roleId = 100L;
			}
			if (platformId==7) {
				roleId = 101L;
			}
			loginUserRoleDTO.setRoleId(roleId);
			List<UserRoleDTO> userRoleDTOList = userRoleFacade.findUserRoleAll(loginUserRoleDTO);
			if (EmptyUtil.isNotEmpty(userRoleDTOList)) {
				loginIsBAM = 1;
			}
			map.put("loginIsBackAccountMannager",loginIsBAM);

			UserExtendDTO userExtendDTO3 = userExtendFacade.findById(userExtendDTO2.getId());
			map.put("isAdministrator",userExtendDTO3.getIsAdministrator());

			list.add(map);
		}
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}


	@Override
	public String saveUserBackground(String mail, String headPicUrl, String name,Long companyId, Long departmentId, Integer sex,
			Long birthday, Long entryTime) {
		if(StringUtils.isNotEmail(mail)){
			throw new BusinessException("该邮箱格式不对,请重新填写");
		}
		//添加主表信息
		UserDTO userDTO = userFacade.userByMail(mail);
		if(EmptyUtil.isNotEmpty(userDTO)){
			throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_UNIQUE, "邮箱已存在");
		}
		if(EmptyUtil.isEmpty(companyId)){
			throw new BusinessException("公司编号不能为空");
		}
		//添加用户邮箱信息
		Long userId = saveUserMail(mail,companyId);
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    	UserExtendVO userExtendVO = new UserExtendVO();
    	userExtendVO.setId(userId);
    	userExtendVO.setHeadPicUrl(headPicUrl);
    	userExtendVO.setName(name);
    	userExtendVO.setSex(sex);
    	if(EmptyUtil.isNotEmpty(birthday)){
    		try {
    			userExtendVO.setBirthday(format.parse(format.format(birthday)));
    		} catch (ParseException e) {
    			throw new BusinessException("用户生日格式转换错误");
    		}
    	}

    	//添加扩展表信息
    	int rows = this.saveUserExtend(userExtendVO);
    	//添加扩展表信息
    	Long saveUserWelfareWithTx = this.saveUserWelfareWithTx(userId, departmentId, entryTime);
    	if(rows != 0 && EmptyUtil.isNotEmpty(saveUserWelfareWithTx)){
    		return "新增用户信息成功";
    	}else{
    		return "新增用户信息失败";
    	}
	}
	//添加用户邮箱、公司信息返回用户id
	public Long saveUserMail(String mail,Long companyId){
		return userFacade.saveUserMail(mail,companyId);
	}
	@Override
	public String updateUserBackground(Long userId, String mail, String headPicUrl, String name,Long companyId, Long departmentId,
			Integer sex, Long birthday, Long entryTime) {
		int i= 0;
		if(EmptyUtil.isEmpty(userId)){
			throw new BusinessException("用户id不能为空");
		}
		if(EmptyUtil.isNotEmpty(mail) || EmptyUtil.isNotEmpty(companyId)){
			i = updateUserMail(userId, mail, companyId);
		}
		if(EmptyUtil.isNotEmpty(name) || EmptyUtil.isNotEmpty(sex) || EmptyUtil.isNotEmpty(birthday)){
			i = updateUserExtend(userId, name, sex, birthday,departmentId);
		}

		if(i == 0){
			return "修改用户信息失败";
		}else{
			return "修改用户信息成功";
		}
	}
	//根据用户id修改用户邮箱、公司信息
	public int updateUserMail(Long userId,String mail,Long companyId){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userId);
		userDTO.setMail(mail);
		userDTO.setCompanyId(companyId);
		return userFacade.updateEncryptInfoByUserWithTx(userDTO);
	}

	/**
	 * 根据公司id按条件查询所有用户信息
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public List<UserWelfare> userAll(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime, Long platformId) {
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		UserExtendDTO dto = new UserExtendDTO();
		//set数据
		dto.setCompanyId(companyId);
		dto.setName(name);
		dto.setDepartmentId(departmentId);
		dto.setSex(sex);
		dto.setPlatformId(platformId);
		try {
			if(EmptyUtil.isNotEmpty(birthdayStartTime)){
				dto.setBirthdayStartTime(format.parse(format.format(birthdayStartTime)));
			}
			if(EmptyUtil.isNotEmpty(birthdayFinishTime)){
				dto.setBirthdayFinishTime(format.parse(format.format(birthdayFinishTime)));
			}
			if(EmptyUtil.isNotEmpty(entryStartTime)){
				dto.setEntryStartTime(format.parse(format.format(entryStartTime)));
			}
			if(EmptyUtil.isNotEmpty(entryFinishTime)){
				dto.setEntryFinishTime(format.parse(format.format(entryFinishTime)));
			}
		} catch (ParseException e) {
			throw new BusinessException("生日或入职时间搜索条件格式转换错误");
		}
		List<UserExtendDTO> userExtendList = userExtendFacade.userAll(dto);
		List<UserWelfare> list = new ArrayList<UserWelfare>();

		for (UserExtendDTO userExtendDTO : userExtendList) {
			UserWelfare userWelfare = new UserWelfare();
			userWelfare.setUserId(userExtendDTO.getId());
			userWelfare.setName(userExtendDTO.getName());
			userWelfare.setSex(String.valueOf(userExtendDTO.getSex()));
			//格式 24小时制：2016-07-06 09:39:58
	        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
	        if(EmptyUtil.isNotEmpty(userExtendDTO.getBirthday())){
	        	String birthday = dFormat.format(userExtendDTO.getBirthday());
				userWelfare.setBirthday(birthday);
	        }
	        if(EmptyUtil.isNotEmpty(userExtendDTO.getEntryTime())){
	        	String entryTime = dFormat.format(userExtendDTO.getEntryTime());
				userWelfare.setEntryTime(entryTime);
	        }
	        userWelfare.setMobile(userExtendDTO.getMobile());
			userWelfare.setMail(userExtendDTO.getMail());
			userWelfare.setDepartmentName(userExtendDTO.getDepartmentName());

			list.add(userWelfare);
		}
		return list;
	}


	@Override
	public JsonResult<Map<String,Object>> searchUsers(String keyWord, Long userId, Long companyId) {
		List<DepMemberVO> dus=null;
		if(EmptyUtil.isNotBlank(keyWord)){
			//查询条件:
			//name like/email like/userId!=userId/companyId=companyId/name!=null
			List<DepMemberDTO> ueList=userExtendFacade.searchUsers(keyWord,userId,companyId);
			dus=DepMemberConverter.toVO(ueList);
		}else{
			dus=new ArrayList<>();
		}
		Map<String,Object> data=new HashMap<>();
		data.put("memList", dus);
		return JsonResult.success(data);
	}
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	@Override
	public int updateUserBackground(Long userId, String headPicUrl) {
		// TODO Auto-generated method stub
		return userExtendFacade.updateUserBackground(userId, headPicUrl);
	}

	@Override
	public JsonResult<Map<String, Object>> resetStatus(Long id) {
		UserExtendDTO userExtendDTO = userExtendFacade.userByUserId(id);
		UserDTO userDTO = userFacade.findUserByID(id);
		if (EmptyUtil.isEmpty(userExtendDTO) || EmptyUtil.isEmpty(userDTO)) {
			return JsonResult.fail("该员工不存在");
		}

		// 状态重置前检查用户是否离职
		if(userExtendDTO.getIsAvailable() == 1){
			return JsonResult.fail("该用户已经是在职状态");
		}

		if (EmptyUtil.isBlank(userExtendDTO.getMail())) {
			return JsonResult.fail("该用户邮箱为空,请先完善用户信息");
		}

		// 用户状态为失效时,查询其对应的邮箱是否已经被其他有效用户使用
		if(userExtendDTO.getAccountStatus() == 1 && EmptyUtil.isNotBlank(userExtendDTO.getMail())){
			UserExtendDTO userExtendDTO_ = new UserExtendDTO();
			userExtendDTO_.setMail(userExtendDTO.getMail());
			userExtendDTO_.setAccountStatus(0);
			List<UserExtendDTO> userExtendDTOList = userExtendFacade.findAlluser(userExtendDTO_);
			for (UserExtendDTO dto : userExtendDTOList) {
				if(!dto.getId().equals(userExtendDTO.getId())){
					return JsonResult.fail("该邮箱已存在有效状态的账户，不可进行状态重置");
				}
			}
		}

		// 改变用户为在职状态和清空用户的离职日期
		UserDTO userDto_ = new UserDTO();
		userDto_.setId(id);
		userDto_.setIsAvailable(1);
		userDto_.setQuitTime(null);
		userFacade.updateUserInfo(userDto_);
		userFacade.updateQuitTime(userDto_);
		// 改变用户为有效状态和清空用户的失效日期
		UserExtendDTO userExtendDTO_ = new UserExtendDTO();
		userExtendDTO_.setId(id);
		userExtendDTO_.setAccountStatus(0);
		userExtendDTO_.setInvalidTime(null);
		userExtendFacade.updateWithTx(userExtendDTO_);
		userExtendFacade.updateInvalidTime(userExtendDTO_);
		return JsonResult.success();
	}

	/**
	 * 设定员工管理员身份
	 * @param id
	 * @return
	 */
	@Override
	public JsonResult<Map<String, Object>> updateBackAccountMannager(Long id,Long clientId) {
		UserExtendDTO userExtendDTO = userExtendFacade.userByUserId(id);
		//修改员工管理员身份
		if (userExtendDTO.getIsAdministrator() == 0) {
			userExtendDTO.setIsAdministrator(1);
		}else {
			userExtendDTO.setIsAdministrator(0);
		}
		userExtendFacade.updateWithTx(userExtendDTO);
		return JsonResult.success();
	}

	/**
	 * 查询所有管理员用户
	 * @return
	 */
	@Override
	public List<Map<String, Object>> userAdminAll() {
		// TODO Auto-generated method stub
		return userExtendFacade.userAdminAll();
	}
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	@Override
	public List<Map<String, Object>> userByMobile(String mobile,Long platformId) {
		// TODO Auto-generated method stub
		return userExtendFacade.userByMobile(mobile,platformId);
	}
	@Override
	public List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto) {
		return userExtendFacade.queryUserByCondition(dto);
	}
	@Override
	public int updateUserExtendInfo(UserExtendDTO userExtend) {
		return userExtendFacade.updateUserExtendInfoWithTx(userExtend);
	}
	@Override
	public List<UserExtendDTO> userExtendByMobile(String mobile,Long platformId) {
		return userExtendFacade.userExtendByMobile(mobile,platformId);
	}
	@Override
	public int switchUserOpenId(Long id, Long userId) {
		// TODO Auto-generated method stub
		return userExtendFacade.switchUserOpenId(id, userId);
	}
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	@Override
	public int weixinSignoutWithTx(String mobile) {

		return userExtendFacade.weixinSignoutWithTx(mobile);
	}
	@Override
	public int bindingChannelIdByUserId(Long userId, String baiDuChannelId,Integer deviceType) {
		return userExtendFacade.bindingChannelIdByUserId(userId, baiDuChannelId,deviceType);
	}
	@Override
	public int signoutByUserId(Long userId) {
		return userExtendFacade.signoutByUserId(userId);
	}

	@Override
	public List<StoreAdminDTO> getStoreAdminAll(StoreAdminDTO storeAdminDTO) {
		return userExtendFacade.getStoreAdminAll(storeAdminDTO);
	}

	@Override
	public List<UserExtendDTO> getUserAdminAll(UserExtendDTO userExtendDTO) {
		return userExtendFacade.getUserAdminAll(userExtendDTO);
	}

	@Override
	public void updateUserExtendWithTx(UserExtendVO userExtendVO) {
		userExtendFacade.updateUserExtendWithTx(UserExtendConverter.toDTO(userExtendVO));
	}


	@Override
	public UserExtendDTO findAdminUserByManage(String mobile, Long platformId) {
		return userExtendFacade.findAdminUserByManage(mobile, platformId);
	}

}
