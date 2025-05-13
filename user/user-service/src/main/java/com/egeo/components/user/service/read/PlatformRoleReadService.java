package com.egeo.components.user.service.read;
import java.util.List;

import com.egeo.components.user.dto.PlatformRoleDTO;

public interface PlatformRoleReadService {

	public List<PlatformRoleDTO> findAll(PlatformRoleDTO platformRoleDTO); 
	
}
	