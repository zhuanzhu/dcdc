/**
 * Created By: XI
 * Created On: 2018-10-16 10:47:20
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniauthRoleGroup;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.controller.views.request.PageParam;
import com.egeo.components.user.controller.views.response.base.PageResponse;
import com.egeo.components.user.dao.write.UniAuthRolesMapper;
import com.egeo.components.user.dao.write.UniAuthUserInfoMapper;
import com.egeo.components.utils.DateHelper;
import com.egeo.components.utils.UtilHelper;
import com.egeo.utils.log.XLogger;
import com.github.pagehelper.PageHelper;
import com.egeo.vo.BaseResponse;

@Service("uniauthRoleService")
public class UniauthRoleService {
	private static final XLogger logger = XLogger.getLogger(UniauthRoleService.class);

	@Autowired
	private UniAuthRolesMapper uniAuthRolesMapper;
	@Autowired
	private UniAuthUserInfoMapper uniAuthUserInfoMapper;

	/**
	 * 根据主键删除记录
	 * 
	 * @param rid
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> deleteByPK(Integer rid, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		if (UtilHelper.isEmpty(rid) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		int userCount = uniAuthUserInfoMapper.findCountByRid(rid);
		if (userCount > 0) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL_NOT_DELETE.getCode());
			response.setMsg(String.format(ResponseCodeEnum.OPERATION_FAIL_NOT_DELETE.getMsg(), "账号"));

			return response;
		}

		UniAuthRoles uniAuthRoles = new UniAuthRoles();
		uniAuthRoles.setRid(rid);
		uniAuthRoles.setRstate(UniAuthRoles.ROLE_STATE_DELETE);

		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniAuthRoles.setUpdateTime(crrTime);

		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniAuthRoles.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		int count = uniAuthRolesMapper.updatePart(uniAuthRoles);

		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}
		// 删除角色组中的角色
		uniAuthRolesMapper.deleteRoleGroupByRid(uniAuthRoles.getRid());
		return response;
	}

	/**
	 * 保存记录
	 * 
	 * @param uniAuthRoles
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> insert(UniAuthRoles uniAuthRoles, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthRoles) || UtilHelper.isEmpty(uniAuthRoles.getRcode())
				|| UtilHelper.isEmpty(uniAuthRoles.getRname())
				|| UtilHelper.isEmpty(uniAuthRoles.getDepartmentCode())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());

			return response;
		}

		// 查询是否以有存在的角色code
		UniAuthRoles condition = new UniAuthRoles();
		condition.setRcode(uniAuthRoles.getRcode());
		int count = uniAuthRolesMapper.findByCount(condition);
		if (count > 0) {
			response.setCode(ResponseCodeEnum.PARAM_HAS_EXIST.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_HAS_EXIST.getMsg(), uniAuthRoles.getRcode()));
			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniAuthRoles.setCreateTime(crrTime);
		uniAuthRoles.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniAuthRoles.setCreateBy(uniAuthUserInfo.getUserId());
			uniAuthRoles.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		// 默认状态为启用
		uniAuthRoles.setRstate(UniAuthRoles.ROLE_STATE_ENABLE);

		// 插入记录
		count = uniAuthRolesMapper.insert(uniAuthRoles);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		for (String roleGroup : uniAuthRoles.getRoleGroups()) {
			UniauthRoleGroup rg = new UniauthRoleGroup(uniAuthRoles.getRid(), roleGroup);
			uniAuthRolesMapper.insertRoleGroupByRid(rg);
		}

		return response;
	}

	/**
	 * 更新记录
	 * 
	 * @param uniAuthRoles
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> update(UniAuthRoles uniAuthRoles, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthRoles) || UtilHelper.isEmpty(uniAuthRoles.getRname())
				|| UtilHelper.isEmpty(uniAuthRoles.getDepartmentCode())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniAuthRoles.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniAuthRoles.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		uniAuthRoles.setRcode(null);

		// 更新记录
		int count = uniAuthRolesMapper.updatePart(uniAuthRoles);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		uniAuthRolesMapper.deleteRoleGroupByRid(uniAuthRoles.getRid());
		for (String roleGroup : uniAuthRoles.getRoleGroups()) {
			UniauthRoleGroup rg = new UniauthRoleGroup(uniAuthRoles.getRid(), roleGroup);
			uniAuthRolesMapper.insertRoleGroupByRid(rg);
		}

		return response;
	}

	/**
	 * 分页查询用户所属的部门及其子部门的角色
	 * 
	 * @param pageParam
	 * @param userUuid
	 * @return
	 */
	public PageResponse<UniAuthRoles> listByPageInfo(PageParam<UniAuthRoles> pageParam, String userUuid) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		if (UtilHelper.isEmpty(roles)) {
			return new PageResponse<>(new ArrayList<>());
		}

		UniAuthRoles param = pageParam.getParams();
		if (UtilHelper.isEmpty(param)) {
			param = new UniAuthRoles();
		}
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			param.setParentCode(roles.get(0).getDepartmentCode());
		}

		PageHelper.startPage(pageParam.getCurrentPage(), pageParam.getPageSize(), true);

		List<UniAuthRoles> uniAuthRoles = uniAuthRolesMapper.listByPageInfo(param);

		return new PageResponse<>(uniAuthRoles);
	}

	/**
	 * 根据userUuid查询用户所属的部门及其子部门的角色
	 * 
	 * @param userUuid
	 * @param departmentCode
	 * @return
	 */
	public List<UniAuthRoles> getByUser(String userUuid, String departmentCode) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);

		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		String parentCode = null;
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			parentCode = roles.get(0).getDepartmentCode();
		}

		return uniAuthRolesMapper.getByUser(parentCode, departmentCode);
	}

	/**
	 * 根据userUuid查询用户所属的部门及其子部门的角色
	 *
	 * @param userUuid
	 * @param departmentCode
	 * @return
	 */
	public List<UniAuthRoles> getByUserOrDept(String userUuid, String departmentCode) {
		List<UniAuthRoles> accountRoles = uniAuthRolesMapper.getAccountRoleByUuid(userUuid);
		Set<String> rcodes = accountRoles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if(CollectionUtils.isNotEmpty(accountRoles) && !rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)){
			return accountRoles;
		}
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		String parentCode = null;
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			parentCode = roles.get(0).getDepartmentCode();
		}

		return uniAuthRolesMapper.getByUser(parentCode, departmentCode);
	}

	/**
	 * @Description 根据参数查询角色列表
	 * @return
	 * @author wangweijian
	 * @time 2018年12月3日 下午2:32:17
	 */
	public List<UniAuthRoles> getByParams(String roleParams) {
		logger.info("方法名:getByParams \t参数：roleParams={}", roleParams);
		List<UniAuthRoles> roles = uniAuthRolesMapper.getByParams(roleParams);
		return roles;
	}

	public List<String> listRoleGroupByRole(Integer rid) {
		return uniAuthRolesMapper.listRoleGroupByRole(rid);
	}
}