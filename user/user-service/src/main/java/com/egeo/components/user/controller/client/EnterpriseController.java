package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.dao.write.EnterpriseMapper;
import com.egeo.components.user.dto.Enterprise;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthDepartmentController
 * Author: EDZ Date: 2018/10/16 16:42 Description: 部门 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--部门管理")
@Controller
@RequestMapping("/client/uniauth/enterprise")
public class EnterpriseController implements EnterpriseClient{
	@Autowired
	private EnterpriseMapper uniauthDepartmentService;
	@ApiOperation("部门管理--分页查询部门列表接口")
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	public Enterprise findById(@RequestBody Integer id) {
		return uniauthDepartmentService.findById(id);
	}

}
