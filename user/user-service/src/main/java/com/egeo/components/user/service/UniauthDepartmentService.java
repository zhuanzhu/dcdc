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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniauthDepartment;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.controller.views.request.PageParam;
import com.egeo.components.user.controller.views.response.base.PageResponse;
import com.egeo.components.user.dao.write.UniAuthRolesMapper;
import com.egeo.components.user.dao.write.UniAuthUserInfoMapper;
import com.egeo.components.user.dao.write.UniauthDepartmentMapper;
import com.egeo.components.utils.DateHelper;
import com.egeo.components.utils.UtilHelper;
import com.github.pagehelper.PageHelper;
import com.egeo.vo.BaseResponse;

@Service("uniauthDepartmentService")
public class UniauthDepartmentService {

	@Autowired
	private UniauthDepartmentMapper uniauthDepartmentMapper;
	@Autowired
	private UniAuthRolesMapper uniAuthRolesMapper;
	@Autowired
	private UniAuthUserInfoMapper uniAuthUserInfoMapper;

	/**
	 * 根据主键删除记录
	 * 
	 * @param departmentId
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> deleteByPK(Integer departmentId, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();
		if (UtilHelper.isEmpty(departmentId) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		UniauthDepartment uniauthDepartment = new UniauthDepartment();
		uniauthDepartment.setDepartmentId(departmentId);

		List<UniauthDepartment> uniauthDepartments = uniauthDepartmentMapper.getByUniauthDepartment(uniauthDepartment);
		if (UtilHelper.isEmpty(uniauthDepartments)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_INVALID.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_IS_INVALID.getMsg(), departmentId));

			return response;
		} else {
			List<UniAuthRoles> list = uniAuthRolesMapper.getByUser(uniauthDepartments.get(0).getDepartmentCode(), null);
			if (!UtilHelper.isEmpty(list)) {
				response.setCode(ResponseCodeEnum.OPERATION_FAIL_NOT_DELETE.getCode());
				response.setMsg(String.format(ResponseCodeEnum.OPERATION_FAIL_NOT_DELETE.getMsg(), "角色"));

				return response;
			}
		}

		uniauthDepartment.setDepartmentState(UniauthDepartment.DEPARTMENT_STATE_DELETE);

		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniauthDepartment.setUpdateTime(crrTime);

		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniauthDepartment.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		int count = uniauthDepartmentMapper.updatePart(uniauthDepartment);

		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		return response;
	}

	/**
	 * 保存记录
	 * 
	 * @param uniauthDepartment
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> insert(UniauthDepartment uniauthDepartment, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniauthDepartment) || UtilHelper.isEmpty(uniauthDepartment.getDepartmentCode())
				|| UtilHelper.isEmpty(uniauthDepartment.getDepartmentName())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());

			return response;
		}

		// 查询是否以有存在的部门code
		UniauthDepartment condition = new UniauthDepartment();
		condition.setDepartmentCode(uniauthDepartment.getDepartmentCode());
		int count = uniauthDepartmentMapper.findByCount(condition);
		if (count > 0) {
			response.setCode(ResponseCodeEnum.PARAM_HAS_EXIST.getCode());
			response.setMsg(
					String.format(ResponseCodeEnum.PARAM_HAS_EXIST.getMsg(), uniauthDepartment.getDepartmentCode()));

			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniauthDepartment.setCreateTime(crrTime);
		uniauthDepartment.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniauthDepartment.setCreateBy(uniAuthUserInfo.getUserId());
			uniauthDepartment.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		// 默认状态为启用
		uniauthDepartment.setDepartmentState(UniauthDepartment.DEPARTMENT_STATE_ENABLE);
		// 设置部门的所有父级
		if (UtilHelper.isEmpty(uniauthDepartment.getParentCode())) {
			uniauthDepartment.setParentCode("$");
			uniauthDepartment.setParentCodes("$-");
		} else {
			condition = new UniauthDepartment();
			condition.setDepartmentCode(uniauthDepartment.getParentCode());

			List<UniauthDepartment> list = uniauthDepartmentMapper.getByUniauthDepartment(condition);
			if (!UtilHelper.isEmpty(list)) {
				uniauthDepartment
						.setParentCodes(list.get(0).getParentCodes() + uniauthDepartment.getParentCode() + "-");
			}
		}

		// 插入记录
		count = uniauthDepartmentMapper.insert(uniauthDepartment);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		return response;
	}

	/**
	 * 更新记录
	 * 
	 * @param uniauthDepartment
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> update(UniauthDepartment uniauthDepartment, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniauthDepartment) || UtilHelper.isEmpty(uniauthDepartment.getDepartmentId())
				|| UtilHelper.isEmpty(uniauthDepartment.getDepartmentName())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniauthDepartment.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniauthDepartment.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		// 设置部门的所有父级
		if (UtilHelper.isEmpty(uniauthDepartment.getParentCode())) {
			uniauthDepartment.setParentCode("$");
			uniauthDepartment.setParentCodes("$-");
		} else {
			UniauthDepartment condition = new UniauthDepartment();
			condition.setDepartmentCode(uniauthDepartment.getParentCode());

			List<UniauthDepartment> list = uniauthDepartmentMapper.getByUniauthDepartment(condition);
			if (!UtilHelper.isEmpty(list)) {
				uniauthDepartment
						.setParentCodes(list.get(0).getParentCodes() + uniauthDepartment.getParentCode() + "-");
			}
		}

		// 修改所有子级对应关系
		UniauthDepartment condition = new UniauthDepartment();
		condition.setDepartmentId(uniauthDepartment.getDepartmentId());
		List<UniauthDepartment> oldUniauthDepartments = uniauthDepartmentMapper.getByUniauthDepartment(condition);
		if (!UtilHelper.isEmpty(oldUniauthDepartments)) {
			String oldParentCodes = oldUniauthDepartments.get(0).getParentCodes()
					+ oldUniauthDepartments.get(0).getDepartmentCode() + "-";
			String newParentCodes = uniauthDepartment.getParentCodes()
					+ oldUniauthDepartments.get(0).getDepartmentCode() + "-";

			if (!oldParentCodes.equals(newParentCodes)) {
				uniauthDepartmentMapper.updateParentCodes(oldParentCodes, newParentCodes,
						uniauthDepartment.getUpdateTime(), uniauthDepartment.getUpdateBy());
			}
		}

		uniauthDepartment.setDepartmentCode(null);

		// 更新记录
		int count = uniauthDepartmentMapper.updatePart(uniauthDepartment);
		if (count < 1) {
			response.setCode(ResponseCodeEnum.OPERATION_FAIL.getCode());
			response.setMsg(ResponseCodeEnum.OPERATION_FAIL.getMsg());
		}

		return response;
	}

	/**
	 * 分页查询用户所属的部门及其子部门
	 * 
	 * @param pageParam
	 * @param userUuid
	 * @return
	 */
	public PageResponse<UniauthDepartment> listByPageInfo(PageParam<UniauthDepartment> pageParam, String userUuid) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		if (UtilHelper.isEmpty(roles)) {
			return new PageResponse<>(new ArrayList<>());
		}

		UniauthDepartment param = pageParam.getParams();
		if (UtilHelper.isEmpty(param)) {
			param = new UniauthDepartment();
		}
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			param.setParentCode(roles.get(0).getDepartmentCode());
		}

		PageHelper.startPage(pageParam.getCurrentPage(), pageParam.getPageSize(), true);

		List<UniauthDepartment> uniauthDepartments = uniauthDepartmentMapper.listByPageInfo(param);

		return new PageResponse<>(uniauthDepartments);
	}

	/**
	 * 根据userUuid查询用户所属的部门及其子部门
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<UniauthDepartment> getByUser(String userUuid) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);

		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		String parentCode = null;
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			parentCode = roles.get(0).getDepartmentCode();
		}

		return uniauthDepartmentMapper.getByParentCode(parentCode);
	}

	public List<UniauthDepartment> getByParentCode(String parentCode) {
		return uniauthDepartmentMapper.getByParentCode(parentCode);
	}
}