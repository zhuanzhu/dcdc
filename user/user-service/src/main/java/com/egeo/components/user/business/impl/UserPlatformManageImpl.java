package com.egeo.components.user.business.impl;
	

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserPlatformManage;
import com.egeo.components.user.converter.UserPlatformConverter;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.facade.UserPlatformFacade;
import com.egeo.components.user.vo.UserPlatformVO;

@Service("userPlatform")
public class UserPlatformManageImpl implements UserPlatformManage{

	
	@Resource(name="userPlatformFacade")
	private UserPlatformFacade userPlatformFacade;

	@Override
	public boolean userIsExitPlatform(UserPlatformDTO userPlatformDTO) {
		
		return userPlatformFacade.userIsExitPlatform(userPlatformDTO);
	}

    @Override
    public String save(UserPlatformVO userPlatformVO) {
        
        
        return userPlatformFacade.save(UserPlatformConverter.toDTO(userPlatformVO));
    }

    @Override
    public String update(UserPlatformVO platformVO) {
        
        return userPlatformFacade.update(UserPlatformConverter.toDTO(platformVO));
    }

    @Override
    public String deleteByUserIdAndPid(UserPlatformVO platformVO) {
        return userPlatformFacade.deleteByUserIdAndPid(UserPlatformConverter.toDTO(platformVO));
    }

    @Override
    public String updateAll(UserPlatformVO platformVO) {
        
        return userPlatformFacade.updateAll(UserPlatformConverter.toDTO(platformVO));
    }

	@Override
	public Integer findUserCountByPlatformId(Long platformId) {
		return userPlatformFacade.findUserCountByPlatformId(platformId);
	}
}
	