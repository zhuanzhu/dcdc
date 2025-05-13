package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UserPlatformDTO;

public interface UserPlatformWriteService {

    String saveWithTx(UserPlatformDTO dto);

    String updateWithTx(UserPlatformDTO dto);

    String deleteByUserIdAndPidWithTx(UserPlatformDTO dto);

    int delUserPlatformWithTx(UserPlatformDTO platformDTO);

    int setUserPlatformWithTx(List<String> setUserPlatform, Long id);

    String updateAllWithTx(UserPlatformDTO dto);

}
	