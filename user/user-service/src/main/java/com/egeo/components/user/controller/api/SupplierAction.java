package com.egeo.components.user.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.bean.UniauthDepartment;
import com.egeo.components.user.dao.write.SupplierMapper;
import com.egeo.components.user.dao.write.UserExtendWriteDAO;
import com.egeo.components.user.dao.write.UserPlatformWriteDAO;
import com.egeo.components.user.dao.write.UserRoleWriteDAO;
import com.egeo.components.user.dao.write.UserWriteDAO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.components.user.po.UserRolePO;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.SaltUtils;
import com.egeo.vo.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthDepartmentController
 * Author: EDZ Date: 2018/10/16 16:42 Description: 部门 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--部门管理")
@Controller
@RequestMapping("/api/uniauth/supplier")
public class SupplierAction {
	@Autowired
	private SupplierMapper uniauthDepartmentService;
	@Autowired
	private UserWriteDAO userWriteDAO;
	@Autowired
	private UserPlatformWriteDAO userPlatformWriteDAO;
	
	@Autowired
	private UserRoleWriteDAO userRoleWriteDAO;
	
	@Autowired
	private UserExtendWriteDAO userExtendWriteDAO;
	
	@ApiOperation("部门管理--分页查询部门列表接口")
	@RequestMapping(value = "/listByPageInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<Enterprise>> listByPageInfo(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestParam("status") Integer status) {
		List<Enterprise> list ;
		if(status==null ||status==-1) {
			list = uniauthDepartmentService.findAllEnterprises();
		}else if(status==1) {
			list = uniauthDepartmentService.findActiveEnterprise();
		}else if(status==0) {
			list = uniauthDepartmentService.findUnActiveEnterprise();
		}else {
			list = null;
		}
		return BaseResponse.success(list);
	}

	@ApiOperation("终止合作")
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> deleteByPK(@RequestParam("departmentId") Integer departmentId) {
		Enterprise data = uniauthDepartmentService.findById(departmentId);
		if(data!=null) {
			if(data.getCooperationState().intValue()==1) {

				if(uniauthDepartmentService.cooperationStop(departmentId)>0) {
					if (Objects.nonNull(data.getAdminId())){
						userPlatformWriteDAO.updateAvailableByUserId(0,data.getAdminId(),7l);
					}
					return BaseResponse.success("终止成功");
				}
			}else {
				return BaseResponse.fail(-1, "非合作中的代理商");
			}
		}
		return BaseResponse.fail(-1, "终止合作失败");
	}

	@ApiOperation("开启合作")
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> start(@RequestParam("departmentId") Integer departmentId) {
		Enterprise data = uniauthDepartmentService.findById(departmentId);
		if(data!=null) {
			if(data.getCooperationState().intValue()==0) {
				if(uniauthDepartmentService.cooperationStart(departmentId)>0) {
					if (Objects.nonNull(data.getAdminId())){
						userPlatformWriteDAO.updateAvailableByUserId(1,data.getAdminId(),7l);
					}
					return BaseResponse.success("开启成功");
				}
			}else {
				return BaseResponse.fail(-1, "代理商状态非审核中");
			}
		}
		return BaseResponse.fail(-1, "开启合作失败");
	}
	@ApiOperation("新增接口")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> insert(Enterprise data) {
		//创建用户
		//1.插入到user表
		String salt = SaltUtils.getRandomSalt();
		UserPO userPO = new UserPO();
		userPO.setMail(data.getMail());
		userPO.setLoginName(data.getMail());
		userPO.setPlatformId(7l);
		userPO.setPassword(MD5Support.MD5(MD5Support.MD5(data.getPwd()), salt));
		userPO.setSalt(salt);
		userPO.setType(5);
		userWriteDAO.insert(userPO);
		Long userId = userPO.getId();
		data.setAdmin_id(userId);
		//分配角色客服
		data.setCreate_millis(new Date());
		if(uniauthDepartmentService.insertEnterprise(data)>0) {
			Enterprise supplier = uniauthDepartmentService.findByAdminId(userPO.getId());
			if(supplier!=null) {
				long supplierId = supplier.getId();
				userPO.setEnterpriseId(supplierId);
				userWriteDAO.update(userPO);
				UserRolePO userRolePo = new UserRolePO();
				userRolePo.setRoleId(10057l);
				userRolePo.setUserId(userId);
				userRolePo.setCreateTime(new Date());
				userRoleWriteDAO.insert(userRolePo);
				UserPlatformPO userPlatformPO = new UserPlatformPO();
				userPlatformPO.setIsAvailable(1);
				userPlatformPO.setUserId(userId);
				userPlatformPO.setPlatformId(7l);
				userPlatformPO.setCreateTime(new Date());
				userPlatformWriteDAO.insert(userPlatformPO);
				
				UserExtendPO userExtendDTO = new UserExtendPO(); 
				userExtendDTO.setId(userId);
				userExtendDTO.setStatus(1);
				userExtendDTO.setAccountStatus(0);
				userExtendDTO.setIsAdministrator(1);
				userExtendDTO.setIsDeleted(0);
				userExtendWriteDAO.insert(userExtendDTO);
			}
			
			return BaseResponse.success();
		}
		return BaseResponse.fail(-1, "新增代理商失败");
	}

	@ApiOperation("修改接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> update(Enterprise data) {
		data.setUpdate_millis(new Date());
		if(uniauthDepartmentService.update(data)>0) {
			return BaseResponse.success();
		}
		return BaseResponse.fail(-1, "更新代理商失败");
	}

	@ApiOperation("设置管理员")
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniauthDepartment>> getByUser(@RequestParam("id") Integer id,@RequestParam("userId") Long userId) {

		if(uniauthDepartmentService.updateAdmin(id, userId)>0) {
			return BaseResponse.success();
		}
		return BaseResponse.fail(-1, "设置代理商管理员失败");

	}
}
