package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AdCodeManage {

	public AdCodeDTO findAdCodeById(AdCodeDTO dto);	

	public PageResult<AdCodeDTO> findAdCodeOfPage(AdCodeDTO dto,Pagination page);

	public List<AdCodeDTO> findAdCodeAll(AdCodeDTO dto);

	Long insertAdCodeWithTx(AdCodeDTO dto);

	int updateAdCodeWithTx(AdCodeDTO dto);

	int deleteAdCodeWithTx(AdCodeDTO dto);
}
	