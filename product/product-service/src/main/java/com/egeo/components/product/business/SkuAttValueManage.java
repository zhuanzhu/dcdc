package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SkuAttValueManage {

	public SkuAttValueDTO findSkuAttValueById(SkuAttValueDTO dto);	

	public PageResult<SkuAttValueDTO> findSkuAttValueOfPage(SkuAttValueDTO dto,Pagination page);

	public List<SkuAttValueDTO> findSkuAttValueAll(SkuAttValueDTO dto);

	Long insertSkuAttValueWithTx(SkuAttValueDTO dto);

	int updateSkuAttValueWithTx(SkuAttValueDTO dto);

	int deleteSkuAttValueWithTx(SkuAttValueDTO dto);
}
	