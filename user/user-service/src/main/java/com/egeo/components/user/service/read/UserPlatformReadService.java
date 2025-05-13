package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.UserPlatformDTO;

public interface UserPlatformReadService {

    boolean userIsExitPlatform(UserPlatformDTO userPlatformDTO);

    UserPlatformDTO findById(UserPlatformDTO userPlatformDTO);

    List<UserPlatformDTO> findAll(UserPlatformDTO userPlatformDTO);
    /**
     * 根据平台id查询其下员工数量
     * @param platformId
     * @return
     */
	Integer findUserCountByPlatformId(Long platformId);

}
