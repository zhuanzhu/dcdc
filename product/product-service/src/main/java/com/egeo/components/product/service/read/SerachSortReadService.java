package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SerachSortReadService {

	public SerachSortDTO findSerachSortById(SerachSortDTO dto);

	public PageResult<SerachSortDTO> findSerachSortOfPage(SerachSortDTO dto,Pagination page);

	public List<SerachSortDTO> findSerachSortAll(SerachSortDTO dto);
}
	