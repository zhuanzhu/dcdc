package com.egeo.components.user.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserRoleManage;
import com.egeo.components.user.converter.UserRoleConverter;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.facade.UserRoleFacade;
import com.egeo.components.user.vo.UserRoleVO;
@Service("userRole")
public class UserRoleManageImpl implements UserRoleManage {
    
    @Resource(name="userRoleFacade")
    private UserRoleFacade userRoleFacade;

    @Override
    public String save(UserRoleVO userRoleVO) {
        return userRoleFacade.save(UserRoleConverter.toDTO(userRoleVO));
    }

    @Override
    public String deleteWithTx(UserRoleVO userRoleVO) {
        
        return userRoleFacade.deleteWithTx(UserRoleConverter.toDTO(userRoleVO));
    }

	@Override
	public List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto) {
		
		return userRoleFacade.findUserRoleAll(dto);
	}
}
