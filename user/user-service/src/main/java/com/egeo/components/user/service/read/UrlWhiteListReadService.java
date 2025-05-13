package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlWhiteListReadService {

	public UrlWhiteListDTO findUrlWhiteListById(UrlWhiteListDTO dto);

	public PageResult<UrlWhiteListDTO> findUrlWhiteListOfPage(UrlWhiteListDTO dto,Pagination page);

	public List<UrlWhiteListDTO> findUrlWhiteListAll(UrlWhiteListDTO dto);

	public List<String> findUrlWhiteList(Long platformId);
}
	