package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SuListReadService {

	public SuListDTO findSuListById(SuListDTO dto);

	public PageResult<SuListDTO> findSuListOfPage(SuListDTO dto,Pagination page);

	public List<SuListDTO> findSuListAll(SuListDTO dto);

	/**
	 * 根据实例id查询商品列表
	 * @param instId
	 * @return
	 */
	public SuListDTO querySuListByInstId(Long instId);
}
	