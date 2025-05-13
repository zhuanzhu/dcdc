package com.egeo.components.user.business;

import com.egeo.components.user.vo.UserPlatformVO;
import com.egeo.components.user.dto.UserPlatformDTO;

public interface UserPlatformManage {

    boolean userIsExitPlatform(UserPlatformDTO userPlatformDTO);

    String save(UserPlatformVO UserPlatformVO);

    String update(UserPlatformVO platformVO);

    String deleteByUserIdAndPid(UserPlatformVO platformVO);

    String updateAll(UserPlatformVO platformVO);
    /**
     * 根据平台id查询其下员工数量
     * @param platformId
     * @return
     */
	Integer findUserCountByPlatformId(Long platformId);

}
