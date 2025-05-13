package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.service.read.UserRoleReadService;
import com.egeo.components.user.service.write.UserRoleWriteService;
import com.egeo.utils.EmptyUtil;

@Component
public class UserRoleFacade {

	@Resource
	private UserRoleWriteService userRoleWriteService;
	
	@Resource
	private UserRoleReadService userRoleReadService;
	
    public String save(UserRoleDTO userRoleDTO) {
    	List<UserRoleDTO> list = userRoleReadService.findUserRoleAll(userRoleDTO);
    	if (EmptyUtil.isEmpty(list)) {
    		
    		return userRoleWriteService.saveWithTx(userRoleDTO);
    	} else {
    		
    		return list.get(0).getId()+"";
    	}
    }

    public String deleteWithTx(UserRoleDTO userRoleDTO) {
        
        return userRoleWriteService.deleteWithTx(userRoleDTO);
    }

	public List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto) {
		
		return userRoleReadService.findUserRoleAll(dto);
	}

}
