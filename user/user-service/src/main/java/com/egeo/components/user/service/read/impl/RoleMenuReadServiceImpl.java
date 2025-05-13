package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.RoleMenuReadService;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.po.RoleMenuPO;
import com.egeo.components.user.dao.read.RoleMenuReadDAO;
import com.egeo.components.user.converter.RoleMenuConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("roleMenuReadService")
public class RoleMenuReadServiceImpl implements RoleMenuReadService {
	@Autowired
	private RoleMenuReadDAO roleMenuReadDAO ;
		
	@Override
	public List<RoleMenuDTO> findAll(RoleMenuDTO roleMenuDTO) {
		
		RoleMenuPO po = RoleMenuConverter.toPO(roleMenuDTO);
		List<RoleMenuPO> poList = roleMenuReadDAO.findAll(po,null);
		
		if(poList != null && poList.size() > 0){
			return  RoleMenuConverter.toDTO(poList);
		}
		
		return null;
	}

	@Override
	public List<RoleMenuDTO> getRoleMenuListByUserId(Long userId,Long platformId) {

		List<RoleMenuPO> poList = roleMenuReadDAO.findRoleMenuListByUserId(userId,platformId);

		if(poList != null && poList.size() > 0){
			return  RoleMenuConverter.toDTO(poList);
		}

		return null;
	}
	
}
	