package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AdCodeReadService {

	public AdCodeDTO findAdCodeById(AdCodeDTO dto);

	public PageResult<AdCodeDTO> findAdCodeOfPage(AdCodeDTO dto,Pagination page);

	public List<AdCodeDTO> findAdCodeAll(AdCodeDTO dto);
}
	