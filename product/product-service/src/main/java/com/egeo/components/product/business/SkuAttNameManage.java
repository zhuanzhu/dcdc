package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SkuAttNameManage {

	public SkuAttNameDTO findSkuAttNameById(SkuAttNameDTO dto);	

	public PageResult<SkuAttNameDTO> findSkuAttNameOfPage(SkuAttNameDTO dto,Pagination page);

	public List<SkuAttNameDTO> findSkuAttNameAll(SkuAttNameDTO dto);

	Long insertSkuAttNameWithTx(SkuAttNameDTO dto);

	int updateSkuAttNameWithTx(SkuAttNameDTO dto);

	int deleteSkuAttNameWithTx(SkuAttNameDTO dto);
}
	