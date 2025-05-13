package com.egeo.components.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.controller.views.response.OperatorInfoResp;
import com.egeo.components.user.controller.views.response.OperatorPersonResp;
import com.egeo.components.user.dao.write.UniAuthRolesMapper;
import com.egeo.components.user.dao.write.UniAuthUserInfoMapper;
import com.egeo.components.user.enums.RoleGroupEnum;
import com.egeo.components.user.vo.UserBaseInfoResponse;
import com.egeo.utils.log.XLogger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author zhou_k 2017年5月26日
 */
@Service
public class UniAuthUserService {
	private static final XLogger logger = XLogger.getLogger(UniAuthUserService.class);
	@Autowired
	private UniAuthRolesMapper uniAuthRolesMapper;
	@Autowired
	private UniAuthUserInfoMapper uniAuthUserInfoMapper;

	public UniAuthUserInfo selectUuid(String uuid) {
		UniAuthUserInfo admin = uniAuthUserInfoMapper.selectUuid(uuid);
		return admin;
	}

	public OperatorInfoResp selectOperatorByMobile(String userMobile) {
		UniAuthUserInfo admin = uniAuthUserInfoMapper.selectOperator(userMobile,
				RoleGroupEnum.LO_YW.getRoleGroupCode());
		if (admin == null) {
			throw new BusinessException(ResponseCodeEnum.OPERATOR_NO_REGISTER);
		}
		OperatorInfoResp response = new OperatorInfoResp();
		response.setOperatorUuid(admin.getUuId());
		response.setUserName(admin.getUserName());
		response.setLoanAidInstitutions(admin.getCompany());
		response.setLoanAidInstitutionsCode(admin.getDepartmentCode());
		return response;
	}

	public OperatorPersonResp selectywMyInfo(String userUuid) {
		UniAuthUserInfo userInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (userInfo == null) {
			throw new BusinessException(ResponseCodeEnum.OPERATOR_NO_REGISTER);
		}
		OperatorPersonResp response = new OperatorPersonResp();
		response.setUserName(userInfo.getUserName());
		response.setUserMobile(userInfo.getUserMobile());
		response.setCompany(userInfo.getCompany());
		return response;
	}

	public List<UserBaseInfoResponse> queryUserAll(UniAuthUserInfo admin) {
		return uniAuthUserInfoMapper.queryUserAll(admin);
	}

	public PageInfo<UserBaseInfoResponse> queryUserPage(int currentPage, int pageSize, UniAuthUserInfo admin) {
		logger.info("方法名:queryUserPage \t参数：currentPage={},pageSize={},admin={}", currentPage, pageSize,
				admin.toString());
		PageHelper.startPage(currentPage, pageSize, true);
		List<UserBaseInfoResponse> allItems = this.queryUserAll(admin);
		for (UserBaseInfoResponse adminTmp : allItems) {
			adminTmp.setPhaseStatusShow("");
			List<UniAuthRoles> roleList = uniAuthRolesMapper.getByParams("CS");
			Integer[] roleIds = null;
			if (roleList.size() > 0) {
				roleIds = new Integer[roleList.size()];
				for (int i = 0; i < roleList.size(); i++) {
					roleIds[i] = roleList.get(i).getRid();
				}
			}
		}
		PageInfo<UserBaseInfoResponse> rslt = new PageInfo<UserBaseInfoResponse>(allItems);
		rslt.setList(allItems);
		return rslt;
	}

}
