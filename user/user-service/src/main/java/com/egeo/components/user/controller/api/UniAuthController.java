package com.egeo.components.user.controller.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniAuthUserInfoPageResponse;
import com.egeo.components.user.service.UniAuthUserService;
import com.egeo.components.user.vo.UserBaseInfoResponse;
import com.github.pagehelper.PageInfo;
import com.egeo.components.user.vo.PageBean;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 登录 Contoroller
 */
@Controller
@RequestMapping("/api/uniauth")
public class UniAuthController {
	private Logger logger = LoggerFactory.getLogger(UniAuthController.class);
	@Autowired
	private UniAuthUserService userService;
	//@Autowired
	//private UniauthRoleService uniauthRoleService;

	/**
	 * 查看所有用户
	 * 
	 * @param userId
	 * @param rid
	 * @param userName
	 * @param isAllot
	 * @param company
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@ApiOperation(value = "查看所有用户")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public UniAuthUserInfoPageResponse getUserAll(
			@ApiParam(value = "用户uuid") @RequestParam(required = false) String uuId,
			@ApiParam(value = "用户帐号") @RequestParam(required = false) String userId,
			@ApiParam(value = "角色Id数组") @RequestParam(required = false) Integer[] rids,
			@ApiParam(value = "用户名") @RequestParam(required = false) String userName,
			@ApiParam(value = "是否锁定") @RequestParam(required = false) String isAllot,
			@ApiParam(value = "公司") @RequestParam(required = false) String company,
			@ApiParam(value = "电话号码") @RequestParam(required = false) String mobile,
			@ApiParam(value = "pageSize") @RequestParam(required = false) Integer pageSize,
			@ApiParam(value = "pageNumber") @RequestParam(required = false) Integer pageNum) {
		UniAuthUserInfo admin = new UniAuthUserInfo(userId, userName);

		UniAuthUserInfoPageResponse resp = new UniAuthUserInfoPageResponse();
		admin.setUuId(uuId);
		admin.setCompany(company);
		admin.setRoleIds(rids);
		admin.setUserMobile(mobile);
		// admin.setRoleId(StringUtils.isNotBlank(rid) ? Integer.parseInt(rid) : 0);
		PageBean<UniAuthUserInfo> pageQuery = new PageBean<UniAuthUserInfo>();
		if (StringUtils.isNotBlank(isAllot))
			admin.setIsAllot(Integer.parseInt(isAllot));
		if (null == pageNum || pageNum < 0) {
			pageQuery.setCurrentPage(1);
		} else {
			pageQuery.setCurrentPage(pageNum);
		}
		if (null == pageSize || pageSize < 1) {
			pageQuery.setPageSize(20);
		} else {
			pageQuery.setPageSize(pageSize);

		}
		// List<Integer> list = new ArrayList<Integer>();
		PageInfo<UserBaseInfoResponse> page = userService.queryUserPage(pageQuery.getCurrentPage(),
				pageQuery.getPageSize(), admin);
		resp.setPage(page);
		logger.info("查看所有用户:{}", JSONObject.toJSONString(resp));
		return resp;
	}

	
	

}