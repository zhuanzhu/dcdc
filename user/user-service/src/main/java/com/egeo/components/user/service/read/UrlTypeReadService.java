package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlTypeReadService {

	public UrlTypeDTO findUrlTypeById(UrlTypeDTO dto);

	public PageResult<UrlTypeDTO> findUrlTypeOfPage(UrlTypeDTO dto,Pagination page);

	public List<UrlTypeDTO> findUrlTypeAll(UrlTypeDTO dto);
}
	