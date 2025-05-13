package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.RoleUrlDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.web.JsonResult;

public interface RoleUrlReadService {

	List<String> getUrlListByUserId(Long userId, Long platformId);

    JsonResult<List<UrlDTO>> showUrl(Long roleId,Long platformId);
    
    List<RoleUrlDTO> findAll(RoleUrlDTO dto);

}
	