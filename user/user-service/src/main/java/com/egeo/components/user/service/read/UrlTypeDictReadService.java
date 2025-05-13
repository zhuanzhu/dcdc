package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlTypeDictReadService {

	public UrlTypeDictDTO findUrlTypeDictById(UrlTypeDictDTO dto);

	public PageResult<UrlTypeDictDTO> findUrlTypeDictOfPage(UrlTypeDictDTO dto,Pagination page);

	public List<UrlTypeDictDTO> findUrlTypeDictAll(UrlTypeDictDTO dto);
}
	