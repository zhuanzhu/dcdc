package com.egeo.components.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniAuthUserRole;
import com.egeo.components.user.bean.UniauthRoleGroup;
import com.egeo.components.user.bean.UserDataPermission;
import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.common.IdGen;
import com.egeo.components.user.common.RedisUtil;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.controller.views.request.PageParam;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoAddParam;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoParam;
import com.egeo.components.user.controller.views.response.OperatorLoginResponse;
import com.egeo.components.user.controller.views.response.RefreshResponse;
import com.egeo.components.user.controller.views.response.UniAuthLoginResponse;
import com.egeo.components.user.controller.views.response.base.PageResponse;
import com.egeo.components.user.dao.write.UniAuthRolesMapper;
import com.egeo.components.user.dao.write.UniAuthUserInfoMapper;
import com.egeo.components.user.dao.write.UniauthUserRoleMapper;
import com.egeo.components.user.enums.DataPermTypeEnum;
import com.egeo.components.user.enums.RoleGroupEnum;
import com.egeo.components.user.vo.Roles;
import com.egeo.components.user.vo.UserBaseInfoResponse;
import com.egeo.components.utils.CacheUtil;
import com.egeo.components.utils.DateHelper;
import com.egeo.components.utils.DateUtil;
import com.egeo.components.utils.UUIDUtil;
import com.egeo.components.utils.UtilHelper;
import com.egeo.config.RuntimeContext;
import com.egeo.token.AccessToken;
import com.egeo.token.TokenEntity;
import com.egeo.token.TokenManager;
import com.egeo.utils.log.XLogger;
import com.github.pagehelper.PageHelper;
import com.egeo.vo.BaseResponse;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthUserInfoService Author:
 * EDZ Date: 2018/10/26 10:57 Description: 账户 History: <author> <time> <version>
 * <desc> 作者姓名 修改时间 版本号 描述
 */
@ConfigurationProperties(prefix = "token")
@Service("uniauthUserInfoService")
public class UniauthUserInfoService {
	private static final XLogger logger = XLogger.getLogger(UniauthUserInfoService.class);

	@Autowired
	private UniAuthRolesMapper uniAuthRolesMapper;
	@Autowired
	private UniAuthUserInfoMapper uniAuthUserInfoMapper;
	@Autowired
	private UniauthUserRoleMapper uniauthUserRoleMapper;
	@Autowired
	private RedisTemplate<String, byte[]> redisTemplate;
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private TokenManager tokenManager;

	@Value("${token.redis.dbIndex}")
	private Integer dbIndex;

	private long asTokenExpire;
	private long rfTokenExpire;

	public long getAsTokenExpire() {
		return asTokenExpire;
	}

	public void setAsTokenExpire(long asTokenExpire) {
		this.asTokenExpire = asTokenExpire;
	}

	public long getRfTokenExpire() {
		return rfTokenExpire;
	}

	public void setRfTokenExpire(long rfTokenExpire) {
		this.rfTokenExpire = rfTokenExpire;
	}

	public PageResponse<UniAuthUserInfo> listByPageInfo(PageParam<UniAuthUserInfoParam> pageParam, String userUuid) {
		logger.info("方法名:listByPageInfo \t参数：pageParam={},userUuid={}", pageParam.toString(), userUuid);
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		if (UtilHelper.isEmpty(roles)) {
			return new PageResponse<>(new ArrayList<>());
		}

		UniAuthUserInfoParam param = pageParam.getParams();
		if (UtilHelper.isEmpty(param)) {
			param = new UniAuthUserInfoParam();
		}
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			param.setParentCode(roles.get(0).getDepartmentCode());
		}

		PageHelper.startPage(pageParam.getCurrentPage(), pageParam.getPageSize(), true);

		List<UniAuthUserInfo> uniAuthUserInfos = uniAuthUserInfoMapper.listByPageInfo(param);
		return new PageResponse<>(uniAuthUserInfos);
	}

/*
	public BaseResponse<List<UniAuthUserInfo>> getUserInfo(UserBussTypeRequest request) {
		logger.info("方法名:getUserInfo \t参数：request={}", JSON.toJSONString(request));
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(request.getUserUuid());
		if (UtilHelper.isEmpty(roles)) {
			return BaseResponse.success();
		}
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			String parentCode = roles.get(0).getDepartmentCode();
			LoanOrganizationReq req = new LoanOrganizationReq();
			req.setLoanOrganizationCode(parentCode);
			BaseResponse<List<LoanOrganizationResp>> resp = loanAgencyClient.getListByCode(req);
			if(null == resp || null == resp.getData() || resp.getData().size() < 1){
				return BaseResponse.success();
			}
			List<LoanOrganizationResp> list = resp.getData();
			*//**
			 * 封装助贷机构业务角色
			 *//*
			List<String> roleQueryList = new ArrayList<>();
			for(LoanOrganizationResp loanOrganization : list){
				roleQueryList.add(loanOrganization.getOrganizationCode() + "-YW");
			}
			List<UniAuthUserInfo> uniAuthUserInfos = uniAuthUserInfoMapper.selectByLoanABussType(roleQueryList, request.getBussType());
			return BaseResponse.success(uniAuthUserInfos);
		}
		return BaseResponse.success();
	}

	public BaseResponse<List<UniAuthUserInfo>> getUserInfoByLoanAid(UserBussTypeRequest request) {
		logger.info("方法名:getUserInfo \t参数：request={}", JSON.toJSONString(request));
		if(StringUtils.isEmpty(request.getLoanAid())){
			return BaseResponse.success();
		}
		LoanOrganizationReq req = new LoanOrganizationReq();
		req.setLoanOrganizationCode(request.getLoanAid());
		BaseResponse<List<LoanOrganizationResp>> resp = loanAgencyClient.getListByCode(req);
		if(null == resp || null == resp.getData() || resp.getData().size() < 1){
			return BaseResponse.success();
		}
		List<LoanOrganizationResp> list = resp.getData();
		*//**
		 * 封装助贷机构业务角色
		 *//*
		List<String> roleQueryList = new ArrayList<>();
		for(LoanOrganizationResp loanOrganization : list){
			roleQueryList.add(loanOrganization.getOrganizationCode() + "-YW");
		}
		List<UniAuthUserInfo> uniAuthUserInfos = uniAuthUserInfoMapper.selectByLoanAid(roleQueryList);
		return BaseResponse.success(uniAuthUserInfos);
	}

	/**
	 * 保存记录
	 *
	 * @param uniAuthUserInfo
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> insert(UniAuthUserInfoAddParam uniAuthUserInfo, String userUuid) {
		logger.info("方法名:insert \t参数：uniAuthUserInfo={},userUuid={}", uniAuthUserInfo.toString(), userUuid);
		BaseResponse<String> response = new BaseResponse<String>();
		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthUserInfo) || UtilHelper.isEmpty(uniAuthUserInfo.getUserId())
				|| UtilHelper.isEmpty(uniAuthUserInfo.getUserName())
				|| UtilHelper.isEmpty(uniAuthUserInfo.getUserMobile())
				|| UtilHelper.isEmpty(uniAuthUserInfo.getUserPwd()) || UtilHelper.isEmpty(uniAuthUserInfo.getRoles())
				|| UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		if (!this.verifyRole(uniAuthUserInfo.getRoles())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_INVALID.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_IS_INVALID.getMsg(), "角色"));
			return response;
		}

		// 查询是否以有存在的userId
		UniAuthUserInfo user = uniAuthUserInfoMapper.selectUserIsNotId(String.valueOf(uniAuthUserInfo.getUserId()));
		if (!UtilHelper.isEmpty(user)) {
			response.setCode(ResponseCodeEnum.PARAM_HAS_EXIST.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_HAS_EXIST.getMsg(), uniAuthUserInfo.getUserId()));
			return response;
		}

		user = new UniAuthUserInfo();
		user.setUserId(String.valueOf(uniAuthUserInfo.getUserId()));
		user.setUserName(uniAuthUserInfo.getUserName());
		user.setUserPwd(uniAuthUserInfo.getUserPwd());
		// user.setUserPwd(StringUtils.getMD5(uniAuthUserInfo.getUserPwd()));
		user.setUserMobile(uniAuthUserInfo.getUserMobile());
		user.setCompany(uniAuthUserInfo.getCompany());
		user.setStatus(Integer.parseInt(uniAuthUserInfo.getRoles().split(",")[0]));
		user.setPhaseStatus(uniAuthUserInfo.getPhaseStatus());

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		user.setCreateDate(crrTime);
		user.setUpdateDate(crrTime);

		// 设置操作人
		UniAuthUserInfo by = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(by)) {
			user.setCreatedBy(by.getUserId());
			user.setUpdateBy(by.getUserId());
		}

		// 默认正常
		user.setIsAllot(0);
		user.setUuId(IdGen.uuid());

		// 插入记录
		int count = uniAuthUserInfoMapper.insert(user);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		// 更新用户角色权限
		this.insertUserRole(user.getId(), uniAuthUserInfo.getRoles());

		this.insertUserAccountRole(user.getId(), uniAuthUserInfo.getAccountAddRoles());

		// 更新用户数据权限
		this.saveUserPermission(user.getUuId(), uniAuthUserInfo);

		return response;
	}

	private void saveUserPermission(String userUuid, UniAuthUserInfoAddParam uniAuthUserInfo) {
		// 助贷机构
		int loanFlag = uniAuthUserInfo.getDatapermLoanFlag();
		List<String> loanCodeList = uniAuthUserInfo.getDatapermLoanCodes();
		UserDataPermission datapermLoan = new UserDataPermission();
		datapermLoan.setUserUuid(userUuid);
		datapermLoan.setType(DataPermTypeEnum.LOAN_ORGANIZATION.getType());
		datapermLoan.setState(loanFlag);
		datapermLoan.setCodesStr(
				loanCodeList != null ? org.apache.commons.lang.StringUtils.join(loanCodeList.toArray(), ",") : "");

		// 资金方
		int capitalFlag = uniAuthUserInfo.getDatapermCapitalFlag();
		List<String> capitalList = uniAuthUserInfo.getDatapermCapitalCodes();
		UserDataPermission datapermCapital = new UserDataPermission();
		datapermCapital.setUserUuid(userUuid);
		datapermCapital.setType(DataPermTypeEnum.CAPITAL.getType());
		datapermCapital.setState(capitalFlag);
		datapermCapital.setCodesStr(
				capitalList != null ? org.apache.commons.lang.StringUtils.join(capitalList.toArray(), ",") : "");

		// 助贷机构
		int bustypeFlag = uniAuthUserInfo.getDatapermBustypeFlag();
		List<String> bustypeList = uniAuthUserInfo.getDatapermBustypeCodes();
		UserDataPermission datapermBustype = new UserDataPermission();
		datapermBustype.setUserUuid(userUuid);
		datapermBustype.setType(DataPermTypeEnum.BUS_TYPE.getType());
		datapermBustype.setState(bustypeFlag);
		datapermBustype.setCodesStr(
				bustypeList != null ? org.apache.commons.lang.StringUtils.join(bustypeList.toArray(), ",") : "");

		// 保存更新
		uniAuthUserInfoMapper.insertUserDataPermission(datapermLoan);
		uniAuthUserInfoMapper.insertUserDataPermission(datapermCapital);
		uniAuthUserInfoMapper.insertUserDataPermission(datapermBustype);

		List<UserDataPermission> redisList = new ArrayList<>();
		if (datapermLoan.getState() == 1) {
			String codesStr = datapermLoan.getCodesStr();
			datapermLoan.setCodesByList(Arrays.asList(codesStr.split(",")));
			datapermLoan.setCodesStr(null);
			redisList.add(datapermLoan);
		}
		if (datapermCapital.getState() == 1) {
			String codesStr = datapermCapital.getCodesStr();
			datapermCapital.setCodesByList(Arrays.asList(codesStr.split(",")));
			datapermCapital.setCodesStr(null);
			redisList.add(datapermCapital);
		}
		if (datapermBustype.getState() == 1) {
			String codesStr = datapermBustype.getCodesStr();
			datapermBustype.setCodesByList(Arrays.asList(codesStr.split(",")));
			datapermBustype.setCodesStr(null);
			redisList.add(datapermBustype);
		}
		// 同步到redis
		redisUtil.selectBase(dbIndex);
		redisUtil.set(Constants.DATA_PERMISSION + userUuid, JSON.toJSONString(redisList));
	}
	/**
	 * 变更用户所属企业id
	 * @param userUuid
	 * @param enterpriseUuid
	 * @return
	 */
	@Transactional
	public Boolean updateEnterpriseUuid(String userUuid,String enterpriseUuid) {
		UniAuthUserInfo user = uniAuthUserInfoMapper.getByUuid(userUuid);
		if(user==null) {
			return null;
		}
		UniAuthUserInfo updateData = new UniAuthUserInfo();
		updateData.setId(user.getId());
		updateData.setUpDate(new Date());
		updateData.setUpdateBy(RuntimeContext.userUuid());
		updateData.setEnterpriseUuid(enterpriseUuid);
		return uniAuthUserInfoMapper.updatePart(updateData)>0;
	}
	

	/**
	 * 保存记录
	 *
	 * @param uniAuthUserInfo
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> update(UniAuthUserInfoAddParam uniAuthUserInfo, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		logger.info("方法名:update \t参数：uniAuthUserInfo={},userUuid={}", uniAuthUserInfo.toString(), userUuid);
		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthUserInfo) || UtilHelper.isEmpty(uniAuthUserInfo.getId())
				|| UtilHelper.isEmpty(uniAuthUserInfo.getUserName()) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		if (!this.verifyRole(uniAuthUserInfo.getRoles())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_INVALID.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_IS_INVALID.getMsg(), "角色"));
			return response;
		}

		UniAuthUserInfo dbUser = uniAuthUserInfoMapper.getByPK(uniAuthUserInfo.getId().intValue());
		if (dbUser == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NOT_FOUND);
		}

		UniAuthUserInfo user = new UniAuthUserInfo();
		user.setId(uniAuthUserInfo.getId().intValue());
		user.setUserName(uniAuthUserInfo.getUserName());
		user.setUserMobile(uniAuthUserInfo.getUserMobile());
		user.setUserPwd(null);
		user.setCompany(uniAuthUserInfo.getCompany());
		user.setPhaseStatus(uniAuthUserInfo.getPhaseStatus());

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		user.setUpdateDate(crrTime);

		// 设置操作人
		UniAuthUserInfo by = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(by)) {
			user.setUpdateBy(by.getUserId());
		}

		// 插入记录
		int count = uniAuthUserInfoMapper.updatePart(user);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		// 更新用户权限
		this.insertUserRole(user.getId(), uniAuthUserInfo.getRoles());
		// 更新用户可添加角色
		this.insertUserAccountRole(user.getId(), uniAuthUserInfo.getAccountAddRoles());

		// 更新用户数据权限
		this.saveUserPermission(dbUser.getUuId(), uniAuthUserInfo);
		return response;
	}

	/**
	 * 角色是否在同一个部门
	 *
	 * @param roles
	 * @return
	 */
	public Boolean verifyRole(String roles) {
		String[] arr = roles.split(",");
		if (arr.length == 1) {
			return true;
		}

		String departmentCode = null;
		for (String rid : arr) {
			UniAuthRoles role = uniAuthRolesMapper.getByPK(Integer.parseInt(rid));
			if (UtilHelper.isEmpty(role) || UtilHelper.isEmpty(role.getDepartmentCode()))
				return false;

			if (UtilHelper.isEmpty(departmentCode))
				departmentCode = role.getDepartmentCode();
			else if (!departmentCode.equals(role.getDepartmentCode()))
				return false;
		}

		return true;
	}

	/**
	 * 更新用户权限
	 *
	 * @param uid
	 * @param roles
	 */
	public void insertUserRole(Integer uid, String roles) {
		UniAuthUserRole uniAuthUserRole = new UniAuthUserRole();
		uniAuthUserRole.setUid(uid);
		uniauthUserRoleMapper.deleteByProperty(uniAuthUserRole);

		String[] arr = roles.split(",");
		List<UniAuthUserRole> list = new ArrayList<>();
		for (String rid : arr) {
			uniAuthUserRole = new UniAuthUserRole();
			uniAuthUserRole.setUid(uid);
			uniAuthUserRole.setRid(Integer.parseInt(rid));

			list.add(uniAuthUserRole);
		}

		uniauthUserRoleMapper.insertBatch(list);
	}

	public void insertUserAccountRole(Integer uid, String roles){
		UniAuthUserRole uniAuthUserRole = new UniAuthUserRole();
		uniAuthUserRole.setUid(uid);
		uniauthUserRoleMapper.deleteByPropertyAccountRole(uniAuthUserRole);
		if(StringUtils.isEmpty(roles)){
			return;
		}
		String[] arr = roles.split(",");
		List<UniAuthUserRole> list = new ArrayList<>();
		for (String rid : arr) {
			uniAuthUserRole = new UniAuthUserRole();
			uniAuthUserRole.setUid(uid);
			uniAuthUserRole.setRid(Integer.parseInt(rid));

			list.add(uniAuthUserRole);
		}
		uniauthUserRoleMapper.insertBatchAccountRole(list);
	}

	/**
	 * 修改状态
	 *
	 * @param ids
	 * @param isAllot
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> batchUpdateStatus(String ids, Integer isAllot, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		logger.info("方法名:batchUpdateStatus \t参数：ids={},isAllot={},userUuid={}", ids, isAllot, userUuid);
		// 验证参数是否为空
		if (UtilHelper.isEmpty(ids) || UtilHelper.isEmpty(isAllot) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		String[] ary = ids.split(",");
		UniAuthUserInfo admin = new UniAuthUserInfo();
		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		admin.setUpdateDate(crrTime);

		// 设置操作人
		UniAuthUserInfo by = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(by)) {
			admin.setUpdateBy(by.getUserId());
		}

		for (String id : ary) {
			admin.setId(Integer.parseInt(id));
			admin.setIsAllot(isAllot);
			uniAuthUserInfoMapper.updatePart(admin);
		}

		for (String id : ary) {
			if (isAllot == 1 || isAllot == 2) {
				try {
					UniAuthUserInfo user = uniAuthUserInfoMapper.getByPK(Integer.parseInt(id));
					logout(Constants.SYS_CODES, user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return response;
	}

	/**
	 * 修改密码
	 *
	 * @param id
	 * @param userPwd
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public int updatePwd(Integer id, String userPwd, String userUuid) {
		logger.info("方法名:updatePwd \t参数：id={},userPwd={},userUuid={}", id, userPwd, userUuid);
		UniAuthUserInfo admin = new UniAuthUserInfo();
		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		admin.setUpdateDate(crrTime);

		// 设置操作人
		UniAuthUserInfo by = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(by)) {
			admin.setUpdateBy(by.getUserId());
		}

		admin.setId(id);
		admin.setUserPwd(userPwd);
		// admin.setUserPwd(StringUtils.getMD5(userPwd));
		int count = uniAuthUserInfoMapper.updatePart(admin);

		UniAuthUserInfo user = uniAuthUserInfoMapper.getByPK(id);
		if (count > 0 && !userUuid.equals(user.getUserUuid())) {
			logout(Constants.SYS_CODES, user);
		}

		return count;
	}

	/**
	 * 验证密码
	 *
	 * @param userUuid
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public BaseResponse<String> updateSelfPwd(String userUuid, String oldPwd, String newPwd) {
		logger.info("方法名:updateSelfPwd \t参数：userUuid={},oldPwd={},newPwd={}", userUuid, oldPwd, newPwd);
		BaseResponse<String> response = new BaseResponse<String>();
		// 验证参数是否为空
		if (UtilHelper.isEmpty(oldPwd) || UtilHelper.isEmpty(newPwd) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());

			return response;
		}

		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (UtilHelper.isEmpty(uniAuthUserInfo)
				|| !org.apache.commons.lang.StringUtils.equals(oldPwd, uniAuthUserInfo.getUserPwd())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_INVALID.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_IS_INVALID.getMsg(), "原密码"));
			return response;
		}

		int count = updatePwd(uniAuthUserInfo.getId(), newPwd, userUuid);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}
		return response;
	}

	/**
	 * 获取账号公司
	 *
	 * @return
	 */
	public List<String> getAllCompany(String userUuid, String departmentCode) {
		logger.info("方法名:getAllCompany \t参数：userUuid={},departmentCode={}", userUuid, departmentCode);
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		String departmentParentCode = null;
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			// 没有管理员权限
			departmentParentCode = roles.get(0).getDepartmentCode();
		}
		logger.info("departmentParentCode={}", departmentParentCode);
		return uniAuthUserInfoMapper.getAllCompany(departmentParentCode, departmentCode);
	}

	@Transactional
	public BaseResponse<UniAuthLoginResponse> login(String sysCode, String userId, String password, String ip,
			String deviceType) throws Exception {
		BaseResponse<UniAuthLoginResponse> response = new BaseResponse<UniAuthLoginResponse>();
		// 检查参数
		if (UtilHelper.isEmpty(userId) || UtilHelper.isEmpty(password)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		userId = userId.trim();

		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.selectUserId(userId);
		if (userInfo == null) {
			// LogstashManager.log("", "未查询到用户信息", "ip", ip, "userId", userId);
			response.setCode(ResponseCodeEnum.USER_NO_REGISTER.getCode());
			response.setMsg(ResponseCodeEnum.USER_NO_REGISTER.getMsg());
			return response;
		}
		if (!userInfo.getUserPwd().equals(password)) {
			response.setCode(ResponseCodeEnum.PASSWORD_OR_USER_INCORRECT.getCode());
			response.setMsg(ResponseCodeEnum.PASSWORD_OR_USER_INCORRECT.getMsg());
			return response;
		}
		if (userInfo.getIsAllot() == 2) {
			response.setCode(ResponseCodeEnum.USER_FREEZE.getCode());
			response.setMsg(ResponseCodeEnum.USER_FREEZE.getMsg());
			return response;
		}
		if (userInfo.getIsAllot() == 1) {
			response.setCode(ResponseCodeEnum.USER_LOCKED.getCode());
			response.setMsg(ResponseCodeEnum.USER_LOCKED.getMsg());
			return response;
		}
		// 生成accessToken
		TokenEntity accessToken = gengerAccessToken(sysCode, userInfo, userId, deviceType, sysCode, "");
		// 生成refreshToken
		TokenEntity refreshToken = gengerRefreshToken(sysCode, userInfo.getUuId(), userId, deviceType, "");

		UniAuthLoginResponse loginResp = new UniAuthLoginResponse();
		loginResp.setAccessToken(accessToken.getToken());
		long accessExpire = accessToken.getExpireAt();
		loginResp.setAccessExpire(String.valueOf(accessExpire));

		loginResp.setRefreshToken(refreshToken.getToken());
		long refreshExpire = refreshToken.getExpireAt();
		loginResp.setRefreshExpire(String.valueOf(refreshExpire));
		loginResp.setUserId(userId);
		loginResp.setUserUuid(userInfo.getUuId());
		loginResp.setUserName(userInfo.getUserName());
		loginResp.setStatus(userInfo.getStatus());

		Map<String, Set<Roles>> roleGroups = new HashMap<>();
		// 处理业务员所属助贷机构
		for (UniAuthRoles uniAuthRoles : userInfo.getRoles()) {
			Roles role = new Roles();
			PropertyUtils.copyProperties(role, uniAuthRoles);
			if (org.apache.commons.lang.StringUtils.isNotBlank(uniAuthRoles.getRoleGroupArr())) {
				String[] roleGroupArr = uniAuthRoles.getRoleGroupArr().split(",");
				// 按照角色组分组
				for (String roleGroup : roleGroupArr) {
					if (roleGroups.containsKey(roleGroup)) {
						roleGroups.get(roleGroup).add(role);
					} else {
						Set<Roles> newGroup = new HashSet<>();
						newGroup.add(role);
						roleGroups.put(roleGroup, newGroup);
					}
				}

				if (org.apache.commons.lang.StringUtils.isBlank(loginResp.getLoanAidInstitutionsCode())
						&& ArrayUtils.contains(roleGroupArr, RoleGroupEnum.LO_YW.getRoleGroupCode())) {
					UserDataPermission userDataPerm = uniAuthUserInfoMapper.getUserDataPermissionOne(userInfo.getUuId(),
							DataPermTypeEnum.LOAN_ORGANIZATION.getType());
					if (userDataPerm != null && userDataPerm.getCodes() != null && userDataPerm.getCodes().size() > 0) {
						loginResp.setLoanAidInstitutionsCode(userDataPerm.getCodes().get(0));
					}
				}
			}
		}
		loginResp.setRoleGroups(roleGroups);
		response.setData(loginResp);
		return response;
	}

	@Transactional
	public void logout(String[] sysCodes, UniAuthUserInfo user) {
		for (String sysCode : sysCodes) {
			String key = Constants.UNIAUTH_USERINFO_CACHE + user.getUserId();
			String accessTokenKey = Constants.UNIAUTH_USER_ACCESS_TOKEN + sysCode + ":" + user.getUuId();
			String refreshTokenKey = Constants.UNIAUTH_USER_REFRESH_TOKEN + sysCode + ":" + user.getUuId();
			// UniAuthUserInfo userInfo = getUserInfo(key, user.getUserId());
			if (user != null) {
				String tokenId = getTokenId(sysCode, user.getUuId());
				String refreshTokenId = getRefreshTokenId(sysCode, user.getUuId());
				deleteCaChe(key);
				deleteCaChe(tokenId);
				deleteCaChe(refreshTokenId);
				deleteCaChe(accessTokenKey);
				deleteCaChe(refreshTokenKey);
			}
		}
	}

	/**
	 * @param appCode
	 * @param mobile
	 * @param passwordMd5
	 * @return
	 * @Description 业务员账号密码登录
	 */
	public OperatorLoginResponse ywLoginByPwd(String appCode, String mobile, String passwordMd5, String deviceType,
			String deviceId) {
		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.selectUserId(mobile);

		if (userInfo == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}
		if (!org.apache.commons.lang.StringUtils.equalsIgnoreCase(userInfo.getUserPwd(), passwordMd5)) {
			// 用户密码不正确
			throw new BusinessException(ResponseCodeEnum.PASSWORD_OR_USER_INCORRECT);
		}
		if (userInfo.getIsAllot() == 2) {
			// 用户账号冻结
			throw new BusinessException(ResponseCodeEnum.USER_FREEZE);
		}
		if (userInfo.getIsAllot() == 1) {
			// 用户已锁定
			throw new BusinessException(ResponseCodeEnum.USER_LOCKED);
		}

		// 判断助贷机构业务员角色拥有购车分期权限才允许登录
		// 角色组判断
		boolean existLoanYwRoleGroup = false;
		List<UniauthRoleGroup> roleGroupList = uniAuthRolesMapper.getRoleGroupByUserUuid(userInfo.getUuId());
		if (roleGroupList != null && roleGroupList.size() > 0) {
			for (UniauthRoleGroup uniauthRoleGroup : roleGroupList) {
				if (org.apache.commons.lang.StringUtils.equals(uniauthRoleGroup.getRoleGroupCode(),
						RoleGroupEnum.LO_YW.getRoleGroupCode())) {
					existLoanYwRoleGroup = true;
					break;
				}
			}
		}
		if (!existLoanYwRoleGroup) {
			logger.info("用户：【id:{},uuid:{}】非助贷机构业务员组的用户", userInfo.getId(), userInfo.getUuId());
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}

		// 数据权限判断
		boolean existLoanYwDataPerm = false;
		UserDataPermission userDataPermission = uniAuthUserInfoMapper.getUserDataPermissionOne(userInfo.getUuId(),
				DataPermTypeEnum.BUS_TYPE.getType());
		if (userDataPermission == null || userDataPermission.getState() == 0) {
			// 不存在或者不限制数据权限，则意味着该用户拥有业务类型维度下的所有数据权限
			existLoanYwDataPerm = true;
		} else {
			List<String> busTypeList = userDataPermission.getCodes();
			if (busTypeList != null && busTypeList.size() > 0) {
				for (String busType : busTypeList) {
					if (org.apache.commons.lang.StringUtils.equals(busType, "BT_FQ")) {
						existLoanYwDataPerm = true;
						break;
					}
				}
			}
		}
		if (!existLoanYwDataPerm) {
			logger.info("用户：【id:{},uuid:{}】为非购车分期的业务员", userInfo.getId(), userInfo.getUuId());
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}

		// 生成accessToken
		TokenEntity accessToken = gengerAccessToken(appCode, userInfo, mobile, deviceType, appCode, deviceId);
		// 生成refreshToken
		TokenEntity refreshToken = gengerRefreshToken(appCode, userInfo.getUuId(), mobile, deviceType, deviceId);

		OperatorLoginResponse loginResp = new OperatorLoginResponse();
		loginResp.setAccessToken(accessToken.getToken());
		long accessExpire = accessToken.getExpireAt();
		loginResp.setAccessExpire(String.valueOf(accessExpire));

		loginResp.setRefreshToken(refreshToken.getToken());
		long refreshExpire = refreshToken.getExpireAt();
		loginResp.setRefreshExpire(String.valueOf(refreshExpire));
		return loginResp;
	}

	/**
	 * 业务员修改密码
	 *
	 * @param userUuid
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public BaseResponse<String> ywModifyPasswd(String userUuid, String oldPassword, String newPassword) {
		BaseResponse<String> response = new BaseResponse<String>();
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (UtilHelper.isEmpty(uniAuthUserInfo)
				|| !org.apache.commons.lang.StringUtils.equalsIgnoreCase(oldPassword, uniAuthUserInfo.getUserPwd())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_INVALID.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_IS_INVALID.getMsg(), "原密码"));
			return response;
		}

		// 修改并登出
		int count = updatePwd(uniAuthUserInfo.getId(), newPassword, userUuid);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}
		return response;
	}

	/**
	 * 业务员版校验验证码
	 *
	 * @param appCode
	 * @param mobile
	 * @param idfyCode
	 * @return
	 * @throws Exception
	 */
	public String ywForgetPwdValidate(String appCode, String mobile, String idfyCode) throws Exception {
		// 校验用户是否存在
		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.selectUserId(mobile);
		if (userInfo == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}

		// 校验验证码是否正确
		String idFyCode = (String) CacheUtil.get(mobile);
		if (null != idFyCode && null != idfyCode) {
			if (!idfyCode.equals(idFyCode)) {
				throw new BusinessException(ResponseCodeEnum.IDENTIFYING_CODE_ERROR);
			}
		} else {
			throw new BusinessException(ResponseCodeEnum.IDENTIFYING_CODE_ERROR);
		}

		StringBuilder userCache = new StringBuilder(Constants.OPERATOR_USERINFO_CACHE);
		userCache.append(appCode + ":" + mobile);
		// 验证码验证通过则验证码立即失效
		CacheUtil.invalidate(mobile);

		String code = UUIDUtil.randomUUID();
		String key = Constants.SMS_CODE_VALIDATE + code;
		redisUtil.setEx(key, userInfo.getUuId(), 10, TimeUnit.MINUTES);
		return code;
	}

	/**
	 * 业务员版忘记密码修改密码
	 *
	 * @param appCode
	 * @param code
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	public void ywForgetPwdSetNew(String appCode, String code, String newPassword) throws Exception {
		String key = Constants.SMS_CODE_VALIDATE + code;
		String userUuid = redisUtil.get(key);
		if (StringUtils.isBlank(userUuid)) {
			throw new BusinessException(ResponseCodeEnum.SET_NEW_PWD_OVERDUE);
		}
		// 校验用户是否存在
		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (userInfo == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}
		int count = updatePwd(userInfo.getId(), newPassword, userUuid);
		if (count < 1) {
			throw new BusinessException(ResponseCodeEnum.OPERATION_FAIL);
		} else {
			// 修改成功，删除缓存
			redisUtil.delete(key);
		}
	}

	public UniAuthUserInfo getByUuid(String userUuid) {
		return uniAuthUserInfoMapper.getByUuid(userUuid);
	}

	public List<UniAuthUserInfo> getByUuids(List<String> userUuids) {
		return uniAuthUserInfoMapper.selectByUuidList(userUuids);
	}
	/**
	 * 操作缓存删除用户信息
	 *
	 * @param key
	 * @return
	 */
	private boolean deleteCaChe(String key) {
		return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
			connection.select(dbIndex);
			try {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(key);

				Long delRes = connection.del(bKey);
				if (delRes == null || delRes <= 0) {
					// logger.error("删除缓存失败"+key);
				} else {
					logger.info("删除缓存成功，键:{}", key);
				}
			} catch (Exception e) {
				logger.error("删除缓存信息异常" + e);
			}
			return true;
		});
	}

	/**
	 * 生成accessToken
	 *
	 * @param user   用户
	 * @param mobile 手机号
	 */
	private TokenEntity gengerAccessToken(String sysCode, UniAuthUserInfo user, String mobile, String deviceType,
			String userDataUUid, String deviceId) {

		// syscode1:toke1,syscode2:token2,syscode3:token3,…………

		// tokenKey
		String tokenKey = UUIDUtil.randomUUID();
		// 前缀:系统编码&用户uuid
		String accessUserUUidKey = Constants.UNIAUTH_USER_ACCESS_TOKEN + sysCode + ":" + user.getUuId();
		// accessToken失效时间
		long tokenExpire = asTokenExpire * 1000;
		long tokenEndMills = DateUtil.getSecondTimestampTwo(tokenExpire);
		// token的Value
		AccessToken token = new AccessToken(user.getUuId(), mobile, deviceType, userDataUUid, deviceId, "",
				System.currentTimeMillis(), 0, 0, 0, "appCode", "productCode");
		token.setCreatMills(
				user.getCreatedDate() == null ? System.currentTimeMillis() : user.getCreatedDate().getTime());
		TokenEntity tokenEntity = new TokenEntity(tokenKey, tokenEndMills, token);
		try {
			// 登录成功生成Token
			tokenManager.addTokenEntity(accessUserUUidKey, tokenKey, tokenEntity, asTokenExpire, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenEntity;
	}

	/**
	 * 生成refreshToken
	 *
	 * @param userUuid 用户UUID
	 * @param mobile   手机号
	 */
	private TokenEntity gengerRefreshToken(String sysCode, String userUuid, String mobile, String deviceType,
			String deviceId) {

		// tokenKey
		String tokenKey = UUIDUtil.randomUUID();
		// 前缀:系统编码&用户uuid
		String accessUserUUidKey = Constants.UNIAUTH_USER_REFRESH_TOKEN + sysCode + ":" + userUuid;
		// refreshToken失效时间
		long tokenExpire = rfTokenExpire * 1000;
		long tokenEndMills = DateUtil.getSecondTimestampTwo(tokenExpire);
		// token的Value
		AccessToken token = new AccessToken(userUuid, mobile, deviceType, sysCode, deviceId, "",
				System.currentTimeMillis(), 0, 0, 0, "appCode", "productCode");
		TokenEntity tokenEntity = new TokenEntity(tokenKey, tokenEndMills, token);
		try {
			// 登录成功生成refreshToken
			tokenManager.addTokenEntity(accessUserUUidKey, tokenKey, tokenEntity, rfTokenExpire, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenEntity;
	}

	private String getTokenId(String sysCode, String userUuid) {
		String accessUserUUidKey = Constants.UNIAUTH_USER_ACCESS_TOKEN + sysCode + ":" + userUuid;
		byte[] data = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
			connection.select(dbIndex);
			try {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(accessUserUUidKey);
				byte[] bvalue = connection.get(bKey);
				if (bvalue != null && bvalue.length > 0) {
					return bvalue;
				}
			} catch (Exception e) {
				logger.error("删除缓存信息异常" + e);
			}
			return null;
		});

		if (data != null && data.length > 0) {
			return new String(data);
		}
		return "";
	}

	private String getRefreshTokenId(String sysCode, String userUuid) {
		String accessUserUUidKey = Constants.UNIAUTH_USER_REFRESH_TOKEN + sysCode + ":" + userUuid;
		byte[] data = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
			connection.select(dbIndex);
			try {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(accessUserUUidKey);
				byte[] bvalue = connection.get(bKey);
				if (bvalue != null && bvalue.length > 0) {
					return bvalue;
				}
			} catch (Exception e) {
				logger.error("删除缓存信息异常" + e);
			}
			return null;
		});

		if (data != null && data.length > 0) {
			return new String(data);
		}
		return "";
	}

	public List<UserBaseInfoResponse> getUniauthUserList(UniAuthUserInfoParam param) {
		logger.info("方法名:getUserListByRoles \t参数：param={}", param.toString());
		List<UserBaseInfoResponse> userList = uniAuthUserInfoMapper.getUniauthUserList(param);
		return userList;
	}

	public void synUserDataPermission() {
		// 查询所有数据权限数据
		List<UserDataPermission> list = uniAuthUserInfoMapper.getAllUserDataPermission();

		List<String> nullList = new ArrayList<>();
		nullList.add("");

		for (UserDataPermission userDataPermission : list) {
			if (userDataPermission.getState() == 1 && userDataPermission.getCodes().size() == 0) {
				// 如果需要数据权限限制，且无具体内容，赋值一个默认list
				userDataPermission.setCodesByList(nullList);
			}
		}
		// 分组处理
		Map<String, List<UserDataPermission>> map = list.stream()
				.collect(Collectors.groupingBy(UserDataPermission::getUserUuid));
		redisUtil.selectBase(dbIndex);
		// 轮询写入redis
		for (Entry<String, List<UserDataPermission>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<UserDataPermission> mapValue = entry.getValue();
			String value = JSON.toJSONString(mapValue);
			redisUtil.set(Constants.DATA_PERMISSION + key, value);
		}
	}

	public RefreshResponse refreshToken(String refreshTokenKey) {
		RefreshResponse resp = new RefreshResponse();
		TokenEntity tokenEntity = tokenManager.getTokenEntity(refreshTokenKey);
		if (tokenEntity == null) {
			throw new BusinessException(ResponseCodeEnum.TOKEN_NOT_FOUND);
		}
		long expireAt = tokenEntity.getExpireAt();
		expireAt = expireAt * 1000;
		// 过期
		if (expireAt <= 0L || expireAt <= System.currentTimeMillis()) {
			throw new BusinessException(ResponseCodeEnum.TOKEN_NOT_FOUND);
		}
		AccessToken oldResreshToken = tokenEntity.getData();
		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.getByUuid(oldResreshToken.getUserUuid());
		if (userInfo == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NO_REGISTER);
		}
		// String userId = oldResreshToken.getUserUuid();
		String mobile = oldResreshToken.getMobile();
		String deviceId = oldResreshToken.getDeviceId();
		String deviceType = oldResreshToken.getDeviceType();
		String sysCode = oldResreshToken.getUserDataUuid();
		// 生成accessToken
		TokenEntity accessToken = gengerAccessToken(sysCode, userInfo, mobile, deviceType, sysCode, deviceId);
		// 生成refreshToken
		TokenEntity refreshToken = gengerRefreshToken(sysCode, userInfo.getUuId(), mobile, deviceType, deviceId);
		resp.setAccessToken(accessToken.getToken());
		long accessExpire = accessToken.getExpireAt();
		resp.setAccessExpire(String.valueOf(accessExpire));
		resp.setRefreshToken(refreshToken.getToken());
		long refreshExpire = refreshToken.getExpireAt();
		resp.setRefreshExpire(String.valueOf(refreshExpire));
		return resp;
	}

	public List<UserDataPermission> getUserDataPermission(String userUuid) {
		return uniAuthUserInfoMapper.getUserDataPermissionByUser(userUuid);
	}
}
