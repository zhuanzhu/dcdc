package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdProductReadService {

	public JdProductDTO findJdProductById(JdProductDTO dto);

	public PageResult<JdProductDTO> findJdProductOfPage(JdProductDTO dto,Pagination page);

	public List<JdProductDTO> findJdProductAll(JdProductDTO dto);

    List<Long> findAllIdList();

	List<JdProductDTO> findJdProductListByIds(List<Long> skuIdList);

	PageResult<JdProductDTO> getJdProductListByParams(Pagination page, Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow);

    Integer findJdProductCountByProfit(Integer profit);

    List<JdProductDTO> findJdProductListByProfit(Integer profit, Integer start, Integer pageSize);

    List<Long> findHavedIdListByIdList(List<Long> ids);
}
	