package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SkuAttNameReadService {

	public SkuAttNameDTO findSkuAttNameById(SkuAttNameDTO dto);

	public PageResult<SkuAttNameDTO> findSkuAttNameOfPage(SkuAttNameDTO dto,Pagination page);

	public List<SkuAttNameDTO> findSkuAttNameAll(SkuAttNameDTO dto);
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	public List<SkuAttNameDTO> findSkuAttNameByskuId(Long skuId);

    Long findLastId();
}
	