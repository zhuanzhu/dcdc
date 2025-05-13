package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.service.read.UserPlatformReadService;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.utils.EmptyUtil;

@Component
public class UserPlatformFacade {

    @Resource
    private UserPlatformReadService userPlatformReadService;
    
    @Resource
    private UserPlatformWriteService userPlatformWriteService;

    public boolean userIsExitPlatform(UserPlatformDTO userPlatformDTO) {
        
        return userPlatformReadService.userIsExitPlatform(userPlatformDTO);
    }

    public String save(UserPlatformDTO dto) {
    	List<UserPlatformDTO> list = userPlatformReadService.findAll(dto);
    	if (EmptyUtil.isEmpty(list)){
    		
    		return userPlatformWriteService.saveWithTx(dto);
    	} else {

    		return list.get(0).getId() + "";
    	}
    }

    public String update(UserPlatformDTO dto) {
        
        return userPlatformWriteService.updateWithTx(dto);
    }

    public String deleteByUserIdAndPid(UserPlatformDTO dto) {
        
        return userPlatformWriteService.deleteByUserIdAndPidWithTx(dto);
    }

    public String updateAll(UserPlatformDTO dto) {
        
        return userPlatformWriteService.updateAllWithTx(dto);
    }
    /**
     * 根据平台id查询其下员工数量
     * @param platformId
     * @return
     */
	public Integer findUserCountByPlatformId(Long platformId) {
		return userPlatformReadService.findUserCountByPlatformId(platformId);
	}

}
