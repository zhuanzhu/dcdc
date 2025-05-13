package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.RoleMenuPlatformReadService;
import com.egeo.components.user.dto.RoleMenuPlatformDTO;
import com.egeo.components.user.po.RoleMenuPlatformPO;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.components.user.dao.read.RoleMenuPlatformReadDAO;
import com.egeo.components.user.converter.RoleMenuPlatformConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("roleMenuPlatformReadService")
public class RoleMenuPlatformReadServiceImpl implements RoleMenuPlatformReadService {
	@Autowired
	private RoleMenuPlatformReadDAO roleMenuPlatformReadDAO ;

	@Override
	public List<String> getMenuList(Long userId, Long platformId) {
		
		UserPlatformPO po = new UserPlatformPO();
		po.setUserId(userId);
		po.setPlatformId(platformId);
		return roleMenuPlatformReadDAO.getMenuList(po);
	}

	@Override
	public List<String> getMenuByUserPlatform(Long userId, Long platformId) {

		UserPlatformPO po = new UserPlatformPO();
		po.setUserId(userId);
		po.setPlatformId(platformId);
		return roleMenuPlatformReadDAO.getMenuByUserPlatform(po);
	}
}
	