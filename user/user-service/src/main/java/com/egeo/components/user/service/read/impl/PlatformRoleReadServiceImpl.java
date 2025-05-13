package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.PlatformRoleReadService;
import com.egeo.components.user.dto.PlatformRoleDTO;
import com.egeo.components.user.po.PlatformRolePO;
import com.egeo.components.user.dao.read.PlatformRoleReadDAO;
import com.egeo.components.user.converter.PlatformRoleConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("platformRoleReadService")
public class PlatformRoleReadServiceImpl implements PlatformRoleReadService {
	@Autowired
	private PlatformRoleReadDAO platformRoleReadDAO ;
		
	@Override
	public List<PlatformRoleDTO> findAll(PlatformRoleDTO platformRoleDTO) {
		
		PlatformRolePO po = PlatformRoleConverter.toPO(platformRoleDTO);
		List<PlatformRolePO> poList = platformRoleReadDAO.findAll(po,null);
		
		if(poList != null && poList.size() > 0){
			return  PlatformRoleConverter.toDTO(poList);
		}
		
		return null;
	}
	
}
	