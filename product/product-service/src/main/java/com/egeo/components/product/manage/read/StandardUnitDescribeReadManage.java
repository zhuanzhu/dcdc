package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitDescribeReadManage {

	public StandardUnitDescribePO findStandardUnitDescribeById(StandardUnitDescribePO po);

	public PageResult<StandardUnitDescribePO> findStandardUnitDescribeOfPage(StandardUnitDescribePO po,Pagination page);

	public List<StandardUnitDescribePO> findStandardUnitDescribeAll(StandardUnitDescribePO po);
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	public String findContentByStandardUnitId(Long standardUnitId);
}
	