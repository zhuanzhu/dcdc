package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.PlatformDTO;

public interface PlatformWriteService {

    int upDateWithTx(PlatformDTO dto);

    Integer deleteByIdWithTx(PlatformDTO dto);

    String saveWithTx(PlatformDTO dto);
}
	