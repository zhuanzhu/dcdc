package com.egeo.components.user.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.bean.RoleGroup;
import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.business.RoleManage;
import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.common.ResultManager;
import com.egeo.components.user.controller.views.request.PageParam;
import com.egeo.components.user.controller.views.response.base.PageResponse;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.enums.RoleGroupEnum;
import com.egeo.components.user.service.UniauthRoleService;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.vo.RoleVO;
import com.egeo.components.utils.UtilHelper;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.log.XLogger;
import com.egeo.vo.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthDepartmentController
 * Author: EDZ Date: 2018/10/16 16:42 Description: 角色 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--角色管理")
@Controller
@RequestMapping("/api/uniauth/role")
public class UniauthRoleController {
	private static final XLogger logger = XLogger.getLogger(UniauthRoleController.class);

	@Autowired
	private RoleManage uniauthRoleService;

	@ApiOperation("角色管理--分页查询角色列表接口")
	@RequestMapping(value = "/listByPageInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<PageResult<RoleDTO>> listByPageInfo(@RequestBody PageParam<RoleVO> pageParam,
			@RequestHeader("userUuid") String userUuid) {
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
		 RoleVO roleVO  = pageParam.getParams();
		 roleVO.setPlatformId(7l);
		 roleVO.setUseable(1);
		 PageResult<RoleDTO> list = uniauthRoleService.roleListPage(page, roleVO);
		 BaseResponse<PageResult<RoleDTO>> rslt = new BaseResponse<PageResult<RoleDTO>>();
		 rslt.setData(list);
		return rslt;
	}

	@ApiOperation("角色管理--分页查询角色列表接口")
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<PageResult<RoleDTO>> all() {
		Pagination page = new Pagination();
		page.setPageNo(1);
		page.setPageSize(1000);
		 RoleVO roleVO  = new RoleVO();
		 roleVO.setPlatformId(7l);
		 roleVO.setUseable(1);
		 PageResult<RoleDTO> list = uniauthRoleService.roleListPage(page, roleVO);
		 BaseResponse<PageResult<RoleDTO>> rslt = new BaseResponse<PageResult<RoleDTO>>();
		 rslt.setData(list);
		return rslt;
	}
	@ApiOperation("角色管理--删除角色接口")
	@RequestMapping(value = "/deleteByPK", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> deleteByPK(@RequestParam("rid") Long rid,
			@RequestHeader("userUuid") String userUuid) {
		RoleDTO roleDto = new RoleDTO();
		roleDto.setId(rid);
		uniauthRoleService.updateRoleStatus(roleDto, 0);
		return BaseResponse.success("删除成功");
	}

	@ApiOperation("角色管理--新增角色接口")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> insert(@RequestBody RoleDTO role,
			@RequestHeader("userUuid") String userUuid) {
		if(role.getPlatformId()==null) {
			role.setPlatformId(7l);
		}
		role.setUseable(1);
		uniauthRoleService.saveRoleWithTx(role);

		return BaseResponse.success("新增角色成功");
	}

	@ApiOperation("角色管理--修改角色接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> update(@RequestBody RoleDTO role,
			@RequestHeader("userUuid") String userUuid) {
		if(role.getPlatformId()==null) {
			role.setPlatformId(7l);
		}
		uniauthRoleService.updateRoleWithTx(role);
		return BaseResponse.success("修改角色成功");
	}

	@ApiOperation("角色管理--根据userUuid查询用户所属的部门及其子部门的角色接口")
	@RequestMapping(value = "/getByUser", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthRoles>> getByUser(
			@RequestParam(value = "departmentCode", required = false) String departmentCode,
			@RequestHeader("userUuid") String userUuid) {
		BaseResponse<List<UniAuthRoles>> response = new BaseResponse<List<UniAuthRoles>>();
		if (UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		List<UniAuthRoles> list = null /*uniauthRoleService.getByUser(userUuid, departmentCode)*/;
		response.setData(list);
		return response;
	}

	@ApiOperation("角色管理--根据userUuid查询用户所属的部门及其子部门的角色接口")
	@RequestMapping(value = "/getRoleBySysCode", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<RoleDTO>> getRoleBySysCode(
			@RequestParam("sysCode") String sysCode) {
		BaseResponse<List<RoleDTO>> response = new BaseResponse<List<RoleDTO>>();
		RoleDTO req = new RoleDTO();
		req.setSysCode(sysCode);
		List<RoleDTO> list = uniauthRoleService.findAll(req);
		response.setData(list);
		return response;
	}
/*
	@ApiOperation("角色管理--根据userUuid查询用户所属的部门及其子部门的角色接口")
	@RequestMapping(value = "/getByUserOrDept", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthRoles>> getByUserOrDept(
			@RequestParam(value = "departmentCode", required = false) String departmentCode) {
		BaseResponse<List<UniAuthRoles>> response = new BaseResponse<List<UniAuthRoles>>();
		List<UniAuthRoles> list = uniauthRoleService.getByUserOrDept(RuntimeContext.userUuid(), departmentCode);
		response.setData(list);
		return response;
	}

	@ApiOperation("根据参数进行角色查询接口")
	@RequestMapping(value = "/listByParams", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthRoles>> listByParams(
			@ApiParam(value = "参数") @RequestParam(value = "roleParams", required = false) String roleParams) {
		BaseResponse<List<UniAuthRoles>> response = new BaseResponse<List<UniAuthRoles>>();
		List<UniAuthRoles> list = uniauthRoleService.getByParams(roleParams);
		response.setData(list);
		return response;
	}

	@ApiOperation("根据参数进行角色查询接口")
	@RequestMapping(value = "/listRoleGroup", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<RoleGroup>> listRoleGroup() {
		BaseResponse<List<RoleGroup>> response = new BaseResponse<List<RoleGroup>>();
		List<RoleGroup> list = new ArrayList<RoleGroup>();
		RoleGroupEnum[] roleGroupList = RoleGroupEnum.values();
		for (RoleGroupEnum roleGroupEnum : roleGroupList) {
			RoleGroup roleGroup = new RoleGroup(roleGroupEnum.getRoleGroupCode(), roleGroupEnum.getRoleGroupName());
			list.add(roleGroup);
		}
		response.setData(list);
		return response;
	}*/
/*
	@ApiOperation("获取某个角色的所有角色组接口")
	@RequestMapping(value = "/listRoleGroupByRole", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<String>> listRoleGroupByRole(@RequestBody UniAuthRoles uniAuthRoles) {
		BaseResponse<List<String>> resp = new BaseResponse<List<String>>();
		try {
			resp.setData(uniauthRoleService.listRoleGroupByRole(uniAuthRoles.getRid()));
		} catch (BusinessException e) {
			resp.setCode(e.getErrNo());
			resp.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			logger.error("获取某个角色的所有角色组异常：", e);
			resp.setCode(ResultManager.CODE_ERROR);
			resp.setMsg(ResultManager.MSG_ERROR);
		}
		return resp;
	}*/

}
