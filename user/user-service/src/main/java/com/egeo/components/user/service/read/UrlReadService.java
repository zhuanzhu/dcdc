package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.UrlDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlReadService {

    List<String> findAll();

    List<UrlDTO> getUrlByRoleId(Long roleId);

    PageResult<UrlDTO> findAll(Pagination page,UrlDTO urlDTO);
    
    List<UrlDTO> findAll(UrlDTO urlDTO);
    
    UrlDTO findUrlById(Long id);
    
}
	