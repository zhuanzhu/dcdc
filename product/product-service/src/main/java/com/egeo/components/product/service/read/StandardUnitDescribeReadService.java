package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitDescribeReadService {

	public StandardUnitDescribeDTO findStandardUnitDescribeById(StandardUnitDescribeDTO dto);

	public PageResult<StandardUnitDescribeDTO> findStandardUnitDescribeOfPage(StandardUnitDescribeDTO dto,Pagination page);

	public List<StandardUnitDescribeDTO> findStandardUnitDescribeAll(StandardUnitDescribeDTO dto);
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	public String findContentByStandardUnitId(Long standardUnitId);
}
	