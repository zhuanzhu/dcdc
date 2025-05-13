package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlTypeDictManage {

	public UrlTypeDictDTO findUrlTypeDictById(UrlTypeDictDTO dto);	

	public PageResult<UrlTypeDictDTO> findUrlTypeDictOfPage(UrlTypeDictDTO dto,Pagination page);

	public List<Map<String, Object>> findUrlTypeDictAll(UrlTypeDictDTO dto);

	Long insertUrlTypeDictWithTx(UrlTypeDictDTO dto);

	int updateUrlTypeDictWithTx(UrlTypeDictDTO dto);

	int deleteUrlTypeDictWithTx(UrlTypeDictDTO dto);
}
	