package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SerachSortManage {

	public SerachSortDTO findSerachSortById(SerachSortDTO dto);	

	public PageResult<SerachSortDTO> findSerachSortOfPage(SerachSortDTO dto,Pagination page);

	public List<SerachSortDTO> findSerachSortAll(SerachSortDTO dto);

	Long insertSerachSortWithTx(SerachSortDTO dto);

	int updateSerachSortWithTx(SerachSortDTO dto);

	int deleteSerachSortWithTx(SerachSortDTO dto);
}
	