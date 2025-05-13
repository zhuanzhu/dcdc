package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.RoleUrlReadManage;
import com.egeo.components.user.dao.read.RoleUrlReadDAO;
import com.egeo.components.user.dao.read.UrlReadDAO;
import com.egeo.components.user.po.RoleUrlPO;
import com.egeo.utils.StringUtils;

@Service
public class RoleUrlReadManageImpl implements RoleUrlReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlReadDAO urlReadDAO;
	@Autowired
	private RoleUrlReadDAO roleUrlReadDAO;
	
	@Override
	public List<String> getUrlListByUserId(Long userId, Long platformId) {
		List<String> userUrlList = new ArrayList<>();
		
		List<String> roleUrlList = urlReadDAO.getUrlListByUserId(userId, platformId);
		List<String> funcUrlList = urlReadDAO.getFunctionUrlListByUserId(userId, platformId);
		if (StringUtils.isNotEmpty(roleUrlList)) {
			logger.info("roleUrl不为空");
			if (roleUrlList.indexOf("userList") > -1) {
				logger.info("有userList");
			}else {
				logger.info("没有userList");
			}
			userUrlList.addAll(roleUrlList);
		}
		if (StringUtils.isNotEmpty(funcUrlList)) {
			userUrlList.addAll(funcUrlList);
		}
		return userUrlList;
	}
	
	@Override
	public List<RoleUrlPO> findAll(RoleUrlPO po) {
		return roleUrlReadDAO.findAll(po,null);
	}
}
	