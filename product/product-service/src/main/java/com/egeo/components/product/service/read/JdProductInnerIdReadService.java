package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdProductInnerIdReadService {

	public JdProductInnerIdDTO findJdProductInnerIdById(JdProductInnerIdDTO dto);

	public PageResult<JdProductInnerIdDTO> findJdProductInnerIdOfPage(JdProductInnerIdDTO dto,Pagination page);

	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto);

    List<JdProductInnerIdDTO> findJdProductInnerIdAllByJdSkuIdList(List<Long> skuIdList);

    Integer findSuProfitById(Long suId);

    Long findPuIdByJdSkuId(Long skuId);
}
	