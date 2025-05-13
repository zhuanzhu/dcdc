package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SkuAttValueReadService {

	public SkuAttValueDTO findSkuAttValueById(SkuAttValueDTO dto);

	public PageResult<SkuAttValueDTO> findSkuAttValueOfPage(SkuAttValueDTO dto,Pagination page);

	public List<SkuAttValueDTO> findSkuAttValueAll(SkuAttValueDTO dto);
}
	