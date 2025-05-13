package com.egeo.components.user.controller.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.business.CompanyManage;
import com.egeo.components.user.business.UserLoginManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.common.ResultManager;
import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.controller.views.request.OperatorReq;
import com.egeo.components.user.controller.views.request.PageParam;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoAddParam;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoParam;
import com.egeo.components.user.controller.views.response.OperatorPersonResp;
import com.egeo.components.user.controller.views.response.UniAuthLoginResponse;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.dao.read.UserReadDAO;
import com.egeo.components.user.dao.write.EnterpriseMapper;
import com.egeo.components.user.dao.write.UserWriteDAO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.dto.User;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.facade.UserRoleFacade;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.service.UniAuthUserService;
import com.egeo.components.user.service.UniauthUserInfoService;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.components.user.vo.UserTempConditionVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.components.utils.HttpUtil;
import com.egeo.components.utils.IPUtil;
import com.egeo.components.utils.ResultUtil;
import com.egeo.components.utils.StringUtil;
import com.egeo.components.utils.UtilHelper;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.vo.BaseResponse;
import com.egeo.web.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthUserInfoController
 * Author: EDZ Date: 2018/10/26 10:53 Description: 用户 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--账户管理")
@Controller
@RequestMapping("/api/uniauth/user")
public class UniauthUserInfoController {
	private static final XLogger logger = XLogger.getLogger(UniauthUserInfoController.class);
	@Autowired
	private UniAuthUserService uniAuthUserService;
	@Autowired
	private UserManage uniauthUserInfoService;
	@Autowired
	private UniauthUserInfoService uniauthService;
	@Autowired
	private UserReadDAO userReadDAO;
	@Autowired
	private UserWriteDAO userWriteDAO;

	@Resource(name="user")
	private UserManage userManage;
	@Autowired
	CompanyManage companyManage;

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	UserReadManage userReadManage;
	@Autowired
	RoleReadService roleReadService;
	@Resource(name = "userLogin")
	private UserLoginManage userLoginManage;

	
	@ApiOperation("账户管理--分页查询账户列表接口")
	@RequestMapping(value = "/listByManage", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<PageResult<UserDTO>> listByManage(@RequestBody PageParam<UniAuthUserInfoParam> pageParam,
			@RequestHeader(value = "userUuid", required = false) String userUuid) {
		
		if (UtilHelper.isEmpty(pageParam)) {
			pageParam = new PageParam<>();
		}
		if (UtilHelper.isEmpty(pageParam.getCurrentPage())) {
			pageParam.setCurrentPage(1);
		}
		if (UtilHelper.isEmpty(pageParam.getPageSize())) {
			pageParam.setPageSize(30);
		}
		Pagination page = new Pagination();
		page.setPageNo(pageParam.getCurrentPage());
		page.setPageSize(pageParam.getPageSize());
		UserPO userVO = new UserPO();
		PageResult<UserCondition> list;
		PageResult<UserDTO> listR = new PageResult<UserDTO>();
		List<UserDTO> data = new ArrayList<UserDTO>();
		if(pageParam.getParams()!=null && pageParam.getParams().getUserName()!=null) {
			userVO.setName(pageParam.getParams().getUserName());
		}
		if(pageParam.getParams()!=null && pageParam.getParams().getUserId()!=null) {
			userVO.setLoginName(pageParam.getParams().getUserId());
		}
		if(pageParam.getParams()!=null && pageParam.getParams().getIsAllot()!=null) {
			userVO.setIsAvailable(pageParam.getParams().getIsAllot());
		}
		list = userReadManage.findPageUser(page,pageParam.getParams().getSysCode(),pageParam.getParams().getRoleId(), userVO);
		for(UserCondition one:list.getList()) {
			UserDTO dto = UserConverter.toDTO2(one);
			dto.setRoles(roleReadService.roleListByUserId(one.getId()));
			if(one.getCompanyId()!=null) {
				Map<String, Object> companyMap = companyManage.findCompanyById(one.getCompanyId());
				if(companyMap!=null && companyMap.containsKey("companyName")) {
					dto.setCompanyName(companyMap.get("companyName").toString());
				}
			}
			if(one.getEnterpriseId()!=null) {
				Enterprise enterprise = enterpriseMapper.findById(one.getEnterpriseId().intValue());
				if(enterprise!=null) {
					dto.setEnterpriseName(enterprise.getName());
				}
			}
			data.add(dto);
		}
		listR.setPageNo(list.getPageNo());
		listR.setPageSize(list.getPageSize());
		listR.setTotalSize(list.getTotalSize());
		listR.setList(data);
		/*
		if(pageParam.getParams()!=null && pageParam.getParams().getEnterpriseId()!=null&& pageParam.getParams().getCompanyId()==null) {
			userVO.setEnterpriseId(pageParam.getParams().getEnterpriseId());
			list = userManage.findPage(page, userVO,2,3);
		}else if(pageParam.getParams()!=null && pageParam.getParams().getEnterpriseId()!=null&& pageParam.getParams().getCompanyId()!=null) {
			userVO.setEnterpriseId(pageParam.getParams().getEnterpriseId());
			userVO.setCompanyId(pageParam.getParams().getCompanyId());
			list = userManage.findPage(page, userVO,3);
		}else {
			list = userManage.findPage(page, userVO,1,2,3);
		}*/
		
		BaseResponse<PageResult<UserDTO>> rslt = new BaseResponse<PageResult<UserDTO>>();
		rslt.setData(listR);
		return rslt;
	}

	@ApiOperation("账户管理--分页查询账户列表接口")
	@RequestMapping(value = "/listByPageInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<PageResult<UserDTO>> listByPageInfo(@RequestBody PageParam<UniAuthUserInfoParam> pageParam,
			@RequestHeader(value = "userUuid", required = false) String userUuid) {
		String userUuidParam = RuntimeContext.accessToken().getUserUuid();
		if (UtilHelper.isEmpty(pageParam)) {
			pageParam = new PageParam<>();
		}
		if (UtilHelper.isEmpty(pageParam.getCurrentPage())) {
			pageParam.setCurrentPage(1);
		}
		if (UtilHelper.isEmpty(pageParam.getPageSize())) {
			pageParam.setPageSize(30);
		}
		Pagination page = new Pagination();
		page.setPageNo(pageParam.getCurrentPage());
		page.setPageSize(pageParam.getPageSize());
		UserVO userVO = new UserVO();
		PageResult<UserDTO> list;
		if(pageParam.getParams()!=null && pageParam.getParams().getUserName()!=null) {
			userVO.setName(pageParam.getParams().getUserName());
		}
		if(pageParam.getParams()!=null && pageParam.getParams().getUserId()!=null) {
			userVO.setLoginName(pageParam.getParams().getUserId());
		}
		if(pageParam.getParams()!=null && pageParam.getParams().getIsAllot()!=null) {
			userVO.setIsAvailable(pageParam.getParams().getIsAllot());
		}
		if(pageParam.getParams()!=null && pageParam.getParams().getEnterpriseId()!=null&& pageParam.getParams().getCompanyId()==null) {
			userVO.setEnterpriseId(pageParam.getParams().getEnterpriseId());
			list = userManage.findPage(page, userVO,2,3);
		}else if(pageParam.getParams()!=null && pageParam.getParams().getEnterpriseId()!=null&& pageParam.getParams().getCompanyId()!=null) {
			userVO.setEnterpriseId(pageParam.getParams().getEnterpriseId());
			userVO.setCompanyId(pageParam.getParams().getCompanyId());
			list = userManage.findPage(page, userVO,3);
		}else {
			list = userManage.findPage(page, userVO,1,2,3);
		}
		
		BaseResponse<PageResult<UserDTO>> rslt = new BaseResponse<PageResult<UserDTO>>();
		rslt.setData(list);
		return rslt;
	}


/*	@ApiOperation("账户管理--获取当前登录账户的助贷机构下所有的进件业务员")
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthUserInfo>> getUserInfo( @RequestBody UserBussTypeRequest request) {
		if(StringUtil.isEmpty(request.getUserUuid())){
			request.setUserUuid(RuntimeContext.accessToken().getUserUuid());
		}
		return  uniauthUserInfoService.getUserInfo(request);
	}

	@ApiOperation("账户管理--获取助贷机构下所有的进件业务员")
	@RequestMapping(value = "/getUserInfoByLoanAid", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthUserInfo>> getUserInfoByLoanAid( @RequestBody UserBussTypeRequest request) {
		if(StringUtil.isEmpty(request.getUserUuid())){
			request.setUserUuid(RuntimeContext.accessToken().getUserUuid());
		}
		return  uniauthUserInfoService.getUserInfoByLoanAid(request);
	}*/


	@ApiOperation("账户管理--新增账户接口")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<UserDTO> insert(
			@ApiParam(value = "用户参数") @RequestBody(required = true) UniAuthUserInfoAddParam uniAuthUserInfoAddParam,
			@ApiParam(value = "操作者uuid") @RequestHeader("userUuid") String userUuid) {
		String userUuidParam = RuntimeContext.accessToken().getUserUuid();
		UserDTO user = uniauthUserInfoService.createManageUser(uniAuthUserInfoAddParam.getUserId(), 
				uniAuthUserInfoAddParam.getUserPwd(), uniAuthUserInfoAddParam.getUserName(), uniAuthUserInfoAddParam.getUserMobile(), 
				uniAuthUserInfoAddParam.getUserMail(), uniAuthUserInfoAddParam.getType(), uniAuthUserInfoAddParam.getEnterpriseId(), 
				uniAuthUserInfoAddParam.getCompanyId(),uniAuthUserInfoAddParam.getRoleSet());
		if(user!=null && user.getId()!=null) {
			user.setPassword("");
			return BaseResponse.success(user);
		}
		return BaseResponse.fail(-1, "创建用户失败");
	}

	@ApiOperation("账户管理--修改账户接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> update(
			@ApiParam(value = "用户参数") @RequestBody(required = true) UniAuthUserInfoAddParam uniAuthUserInfoAddParam,
			@ApiParam(value = "操作者uuid") @RequestHeader(name = "userUuid", required = false) Long userUuid) {
		BaseResponse<String> resp = new BaseResponse<String>();
		try {
			return uniauthUserInfoService.updateManageUser(uniAuthUserInfoAddParam);
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("修改账户异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;

	}

	@ApiOperation("账户管理--修改账户状态接口")
	@ResponseBody
	@RequestMapping(value = "/batchUpdateStatus", method = RequestMethod.POST)
	public BaseResponse<String> batchUpdateStatus(
			@ApiParam(value = "用户id") @RequestParam(name = "ids", required = true) String ids,
			@ApiParam(value = "用户状态 0正常 1锁定 2冻结") @RequestParam(name = "isAllot", required = true) Integer isAllot,
			@ApiParam(value = "登录者uuid") @RequestHeader(name = "userUuid", required = false) String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		List<Long> idList = new ArrayList<Long>();
		// 验证参数是否为空
		if (UtilHelper.isEmpty(ids) || UtilHelper.isEmpty(isAllot) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}
		logger.info("方法名:batchUpdateStatus \t参数：ids={},isAllot={},userUuid={}", ids, isAllot, userUuid);
		String[] idStrs = ids.split(",");
		if(idStrs.length>0) {
			for(String one:idStrs) {
				if(StringUtil.isNumeric(one)) {
					idList.add(Long.valueOf(one));
				}
			}
		}
		if(idList.size()>0) {
			if(isAllot>=0) {
				userWriteDAO.updateManageAccountStatus(idList, isAllot);
			}else {
				userWriteDAO.deleteManageAccountStatus(idList);
			}
			
		}
		return BaseResponse.success("成功");
	}

	@ApiOperation("账户管理--管理员修改账户密码接口")
	@ResponseBody
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public BaseResponse<String> updatePwd(
			@ApiParam(value = "用户id") @RequestParam(name = "userId", required = true) Long userId,
			@ApiParam(value = "用户密码") @RequestParam(name = "userPwd", required = true) String userPwd,
			@ApiParam(value = "登录者uuid") @RequestHeader(name = "userUuid", required = false) String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		String userUuidParam = RuntimeContext.accessToken().getUserUuid();
		// 验证参数是否为空
		if (UtilHelper.isEmpty(userId) || UtilHelper.isEmpty(userPwd) || UtilHelper.isEmpty(userUuidParam)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		int count = uniauthUserInfoService.resetManageUserPasswordWithTx(userId, userPwd);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}
		return response;
	}
	@RequestMapping(value = "/update2", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> insertOrUpdateUser(@RequestParam("loginName") String loginName,@RequestParam("mobile") String mobile
			,@RequestParam("email") String email,@RequestParam("newPwd") String newPwd
			,@RequestParam("enterpriseId") Long enterpriseId,@RequestParam("companyId") Long companyId
			,@RequestParam("roleIds") String roleIds,@RequestParam("id") Long id,@RequestParam("type") Integer type) {
		userManage.insertOrUpdateManageUser(loginName,mobile,email,newPwd, enterpriseId, companyId,roleIds, id,type);
		return BaseResponse.success();
	}
	@ApiOperation("账户管理--修改自己账户密码接口")
	@ResponseBody
	@RequestMapping(value = "/updateSelfPwd", method = RequestMethod.POST)
	public BaseResponse<String> updateSelfPwd(@ApiParam(value = "用户旧密码") @RequestParam("oldPwd") String oldPwd,
			@ApiParam(value = "用户新密码") @RequestParam("newPwd") String newPwd) {
		Long userId = RuntimeContext.cacheUser().getId();
		uniauthUserInfoService.updateUserPassword(userId, newPwd, oldPwd);
		return BaseResponse.success("修改密码成功");
	}



	/**
	 * 登录
	 *
	 * @param userId
	 * @param password
	 */
	@ApiOperation(value = "用户登录")
	@RequestMapping(value = "/pwd/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<User> login(@ApiParam(value = "用户名(必传)") @RequestParam("userId") String userId,
			@ApiParam(value = "密码(必传)") @RequestParam("password") String password,
			@ApiParam(value = "系统编码(必传)") @RequestParam("sysCode") String sysCode, HttpServletRequest req, HttpServletResponse resp) {
		String ip = IPUtil.getClientRemoteIp(req);
		JsonResult<User> returnResp = null;
		try {
			UserVO user = new UserVO();
			user.setLoginName(userId);
			user.setPassword(password);
			user.setPlatformId(7l);
			if(sysCode!=null && sysCode.length()>0 && sysCode.equalsIgnoreCase("permission_sys"));
			returnResp = uniauthUserInfoService.manageLogin(user,req,resp);
			if(returnResp!=null && returnResp.getCode()==0 ) {
				return BaseResponse.success(returnResp.getData()==null?null:returnResp.getData());
			}else {
				return BaseResponse.fail(returnResp.getCode(),returnResp.getError());
			}
			// 写登陆日志
			//userLoginManage.insertLoginLogWithTx(rt, req,UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus(),user.getKeyMessage(),user.getStoreId());

		} catch (Exception e) {
			logger.error("登录异常", e);
		}
		return BaseResponse.fail(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM, "登录失败");
	}

	@ApiOperation(value = "用户登录")
	@RequestMapping(value = "/permission/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<UniAuthLoginResponse> loginPermission(@ApiParam(value = "用户名(必传)") @RequestParam("userId") String userId,
			@ApiParam(value = "密码(必传)") @RequestParam("password") String password,
			@ApiParam(value = "系统编码(必传)") @RequestParam("sysCode") String sysCode, HttpServletRequest req, HttpServletResponse resp) {
		String ip = IPUtil.getClientRemoteIp(req);
		JsonResult<User> returnResp = null;
		try {
			UserVO user = new UserVO();
			user.setLoginName(userId);
			user.setPassword(password);
			if(sysCode!=null && sysCode.length()>0 && sysCode.equalsIgnoreCase("permission_sys")) {
				return uniauthService.login(sysCode, userId, password, ip,"");
			}
			// 写登陆日志
			//userLoginManage.insertLoginLogWithTx(rt, req,UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus(),user.getKeyMessage(),user.getStoreId());

		} catch (Exception e) {
			logger.error("登录异常", e);
		}
		return BaseResponse.fail(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM, "登录失败");
	}
/*
	*//**
	 * 用户登出
	 *//*
	@ApiOperation(value = "用户登出")
	@RequestMapping(value = "/pwd/logout", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> logout() {
		if (RuntimeContext.accessToken() != null) {
			String userUuidParam = RuntimeContext.accessToken().getUserUuid();
			String sysCode = RuntimeContext.accessToken().getUserDataUuid();// 后台系统登录时，userdatauuid存储syscode
			UniAuthUserInfo user = uniauthUserInfoService.(userUuidParam);
			if (!UtilHelper.isEmpty(user)) {
				uniauthUserInfoService.logout(new String[] { sysCode }, user);
				// LogstashManager.log("login", "用户登出", "userId", user.getUserId());
			}
		}
		return new BaseResponse<String>();
	}*/

	/**
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Description 根据角色查询用户
	 * @author wangweijian
	 * @time 2018年12月3日 下午2:53:28
	 */
	/*@ApiOperation(value = "查询用户")
	@RequestMapping(value = "/getUniauthUserList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UserBaseInfoResponse>> getUniauthUserList(
			@ApiParam(value = "用户uuid数组") @RequestParam(required = false) Long[] userUuids,
			@ApiParam(value = "角色Id数组") @RequestParam(required = false) Integer[] roleIds,
			@ApiParam(value = "状态数组，0正常 1锁定 2删除") @RequestParam(required = false) Integer[] isAllots,
			@ApiParam(value = "用户帐号") @RequestParam(required = false) String userId,
			@ApiParam(value = "用户名") @RequestParam(required = false) String userName) {
		BaseResponse<List<UserBaseInfoResponse>> returnResp = new BaseResponse<>();
		try {
			List<UserBaseInfoResponse> userList = userReadDAO.findUser(userDTO)(param);

			returnResp.setData(userList);
		} catch (BusinessException e) {
			returnResp.setCode(e.getErrNo());
			returnResp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("查询用户异常：", e);
			returnResp.setCode(ResultManager.CODE_ERROR);
			returnResp.setMsg(ResultManager.MSG_ERROR);
		}
		return returnResp;
	}
*/
	@RequestMapping(value = "/{userUuid}/get", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<UserDTO> getUniauthUserInfo(@RequestParam("userUuid") Long userUuid) {
		BaseResponse<UserDTO> resp = new BaseResponse<UserDTO>();
		try {
			UserDTO rslt = uniauthUserInfoService.findUserByID(userUuid);
			if (rslt == null) {
				return ResultUtil.fail(ResponseCodeEnum.USER_NO_REGISTER, UserDTO.class);
			}
			rslt.setPassword("");
			resp.setData(rslt);
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error(" 异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}
/*
	@RequestMapping(value = "/selectOperatorByMobile", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<OperatorInfoResp> selectOperatorByMobile(
			@RequestBody(required = false) OperatorRequest operatorRequest) {
		BaseResponse<OperatorInfoResp> resp = new BaseResponse<>();
		try {
			OperatorInfoResp infoResp = uniAuthUserService.selectOperatorByMobile(operatorRequest.getMobile());
			if (infoResp == null) {
				return ResultUtil.fail(ResponseCodeEnum.USER_NO_REGISTER, OperatorInfoResp.class);
			}
			resp.setData(infoResp);
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error(" 异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}
*/
	@RequestMapping(value = "/ywMyInfo", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<OperatorPersonResp> selectywMyInfo(@RequestBody(required = false) OperatorReq param) {
		BaseResponse<OperatorPersonResp> resp = new BaseResponse<>();
		try {
			String userUuid = param.getUserUuid() == null ? RuntimeContext.accessToken().getUserUuid()
					: param.getUserUuid();
			if (StringUtils.isBlank(userUuid)) {
				HttpUtil.logParam("UniauthUserInfoController.selectywMyInfo", new String[] { "userUuid" },
						new Object[] { userUuid });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, OperatorPersonResp.class);
			}
			resp.setData(uniAuthUserService.selectywMyInfo(userUuid));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("查询业务员个人信息异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}
/*
	@ApiModelProperty("业务员版用户密码登录")
	@RequestMapping(value = "/ywLoginByPwd", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<OperatorLoginResponse> ywLoginByPwd(
			@ApiParam(value = "业务参数(必传)") @RequestBody OperatorLoginRequest params) {
		BaseResponse<OperatorLoginResponse> resp = new BaseResponse<>();
		try {
			String mobile = params.getMobile();
			String passwordMd5 = params.getPasswordMd5();
			CommonHeader commonHeader = HeaderUtils.getCommonHeader();
			logger.info("UserController.login请求头：{}", JSON.toJSONString(commonHeader));
			String appCode = commonHeader.getAppCode();
			String deviceType = commonHeader.getDeviceType();
			String deviceId = commonHeader.getDeviceId();
			// 检查参数
			if (StringUtils.isBlank(mobile) || StringUtils.isBlank(passwordMd5) || StringUtils.isBlank(appCode)
					|| StringUtils.isBlank(deviceType) || StringUtils.isBlank(deviceId)) {
				HttpUtil.logParam("UserController.ywLoginByPwd",
						new String[] { "mobile", "passwordMd5", "appCode", "deviceType", "deviceId" },
						new Object[] { mobile, passwordMd5, appCode, deviceType, deviceId });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, OperatorLoginResponse.class);
			}
			resp.setData(uniauthUserInfoService.ywLoginByPwd(appCode, mobile, passwordMd5, deviceType, deviceId));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("业务员版用户密码登录异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/

	/**
	 * @param params
	 * @return
	 * @Description 业务员版用户修改密码，密码修改成功，请重新登录
	 *//*
	@ApiModelProperty("业务员版用户修改密码")
	@RequestMapping(value = "/ywModifyPasswd", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<String> ywModifyPasswd(
			@ApiParam(value = "业务参数(必传)") @RequestBody OperatorModifyPwdRequest params) {
		BaseResponse<String> resp = new BaseResponse<>();
		try {
			String userUuid = RuntimeContext.accessToken().getUserUuid();
			String oldPassword = params.getOldPassword();
			String newPassword = params.getNewPassword();
			// 检查参数
			if (StringUtils.isBlank(userUuid) || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
				HttpUtil.logParam("UserController.ywModifyPasswd",
						new String[] { "oldPassword", "passwordMd5", "userUuid" },
						new Object[] { oldPassword, newPassword, userUuid });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, String.class);
			}
			resp = uniauthUserInfoService.ywModifyPasswd(userUuid, oldPassword, newPassword);
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("业务员版用户修改密码异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}

	*//**
	 * @param params
	 * @return
	 * @Description 业务员版用户忘记密码校验验证码
	 *//*
	@ApiModelProperty("业务员版忘记密码校验验证码")
	@RequestMapping(value = "/ywForgetPwdValidate", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<String> ywForgetPwdValidate(
			@ApiParam(value = "业务参数(必传)") @RequestBody OperatorForgetPwdRequest params) {
		BaseResponse<String> resp = new BaseResponse<>();
		try {
			String mobile = params.getMobile();
			String idfyCode = params.getIdfyCode();
			CommonHeader commonHeader = HeaderUtils.getCommonHeader();
			logger.info("UserController.login请求头：{}", JSON.toJSONString(commonHeader));
			String appCode = commonHeader.getAppCode();
			// 检查参数
			if (StringUtils.isBlank(mobile) || StringUtils.isBlank(idfyCode) || StringUtils.isBlank(appCode)) {
				HttpUtil.logParam("UserController.ywForgetPwdValidate",
						new String[] { "mobile", "idfyCode", "appCode" }, new Object[] { mobile, idfyCode, appCode });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, String.class);
			}
			resp.setData(uniauthUserInfoService.ywForgetPwdValidate(appCode, mobile, idfyCode));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("业务员版校验验证码异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/
/*
	@ApiModelProperty("业务员版忘记密码设置新密码")
	@RequestMapping(value = "/ywForgetPwdSetNew", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<String> ywForgetPwdSetNew(
			@ApiParam(value = "业务参数(必传)") @RequestBody OperatorSetPwdRequest params) {
		BaseResponse<String> resp = new BaseResponse<String>();
		try {
			String code = params.getCode();
			String newPassword = params.getNewPassword();
			CommonHeader commonHeader = HeaderUtils.getCommonHeader();
			logger.info("UserController.login请求头：{}", JSON.toJSONString(commonHeader));
			String appCode = commonHeader.getAppCode();
			// 检查参数
			if (StringUtils.isBlank(code) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(appCode)) {
				HttpUtil.logParam("UserController.ywForgetPwdSetNew", new String[] { "code", "newPassword", "appCode" },
						new Object[] { code, newPassword, appCode });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, String.class);
			}
			uniauthUserInfoService.ywForgetPwdSetNew(appCode, code, newPassword);
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("业务员版校验验证码异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/

/*	@ApiModelProperty("同步用户数据权限")
	@RequestMapping(value = "/synUserDataPermission", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<String> synUserDataPermission() {
		BaseResponse<String> resp = new BaseResponse<>();
		try {
			uniauthUserInfoService.synUserDataPermission();
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("同步用户数据权限异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/
/*
	@ApiModelProperty("同步用户数据权限")
	@RequestMapping(value = "/getUserDataPermission", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse<List<UserDataPermission>> getUserDataPermission(@RequestParam("userUuid") String userUuid) {
		BaseResponse<List<UserDataPermission>> resp = new BaseResponse<>();
		try {
			if (StringUtils.isBlank(userUuid)) {
				return ResultUtil.fail2List(ResponseCodeEnum.PARAM_IS_NULL, UserDataPermission.class);
			}
			resp.setData(uniauthUserInfoService.getUserDataPermission(userUuid));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("同步用户数据权限异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/
/*
	*//**
	 * 刷新token
	 *
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws JSONException
	 *//*
	@ApiOperation(value = "刷新token")
	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<RefreshResponse> refreshToken(
			@ApiParam(value = "业务参数(必传)") @RequestBody(required = false) UserTokenRequest params) {
		BaseResponse<RefreshResponse> resp = new BaseResponse<RefreshResponse>();
		resp.setData(new RefreshResponse());
		try {
			if (params == null) {
				params = new UserTokenRequest();
			}
			String refreshToken = params.getRefreshToken();

			if (!HttpUtil.CheckParamString(refreshToken)) {
				HttpUtil.logParam("UniauthUserInfoController.refreshToken", new String[] { "refreshToken" },
						new Object[] { refreshToken });
				return ResultUtil.fail(ResponseCodeEnum.PARAM_IS_NULL, RefreshResponse.class);
			}
			resp.setData(uniauthUserInfoService.refreshToken(refreshToken));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("刷新token异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/
}
