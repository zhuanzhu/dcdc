package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlWhiteListManage {

	public UrlWhiteListDTO findUrlWhiteListById(UrlWhiteListDTO dto);	

	public PageResult<UrlWhiteListDTO> findUrlWhiteListOfPage(UrlWhiteListDTO dto,Pagination page);

	public List<UrlWhiteListDTO> findUrlWhiteListAll(UrlWhiteListDTO dto);

	public List<String> findUrlWhiteList(Long platformId);

	Long insertUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList);

	int updateUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList);

	int deleteUrlWhiteListWithTx(UrlWhiteListDTO dto);
	
	/**
	 * 刷新白名单
	 */
	void refreshCacheUrlWhiteList();
}
	