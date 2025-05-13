package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PlatformReadService {

    PageResult<PlatformDTO> findAll(PlatformDTO dto,Pagination page);

    List<PlatformDTO> PlatformByUserId(Long useId);

    PlatformDTO find(Long long1);
    
    List<PlatformDTO> PlatformByUId(Long userId);

    List<PlatformDTO> PlatformByUid(Long userId);
    
    List<PlatformDTO> findAllPlatform();
    
}
	