package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlTypeManage {

	public UrlTypeDTO findUrlTypeById(UrlTypeDTO dto);	

	public PageResult<UrlTypeDTO> findUrlTypeOfPage(UrlTypeDTO dto,Pagination page);

	public List<UrlTypeDTO> findUrlTypeAll(UrlTypeDTO dto);

	Long insertUrlTypeWithTx(UrlTypeDTO dto);

	int updateUrlTypeWithTx(UrlTypeDTO dto);

	int deleteUrlTypeWithTx(UrlTypeDTO dto);
}
	