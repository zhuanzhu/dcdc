package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.SupplierClient;
import com.egeo.components.user.dao.write.SupplierMapper;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.facade.UserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthDepartmentController
 * Author: EDZ Date: 2018/10/16 16:42 Description: 部门 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--部门管理")
@Controller
@RequestMapping("/client/uniauth/supplier")
public class SupplierController implements SupplierClient{
	@Autowired
	private SupplierMapper uniauthDepartmentService;
	@Autowired
	private UserFacade userFacade;
	@ApiOperation("部门管理--分页查询部门列表接口")
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	public Enterprise findById(@RequestBody Integer id) {
		Enterprise rslt = uniauthDepartmentService.findById(id);
		UserDTO user = userFacade.findUserByID(rslt.getAdminId());
		if(user!=null && user.getLoginName()!=null) {
			rslt.setAdminLoginName(user.getLoginName());
		}
		return rslt;
	}

	@ApiOperation("根据名称查询供应商")
	@RequestMapping(value = "/findByName", method = RequestMethod.POST)
	@ResponseBody
	public Enterprise findByName(@RequestBody String supplierName) {
		Enterprise rslt = uniauthDepartmentService.findByName(supplierName);
		if (rslt !=null){
			UserDTO user = userFacade.findUserByID(rslt.getAdminId());
			if(user!=null && user.getLoginName()!=null) {
				rslt.setAdminLoginName(user.getLoginName());
			}
		}
		return rslt;
	}


	@RequestMapping(value = "/findByIds", method = RequestMethod.POST)
	@ResponseBody
	public List<Enterprise> findByIds(@RequestBody List<Long> ids) {
		return uniauthDepartmentService.findByIds(ids);
	}
}
