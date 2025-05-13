package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitTagReadManage {

	public StandardUnitTagPO findStandardUnitTagById(StandardUnitTagPO po);

	public PageResult<StandardUnitTagPO> findStandardUnitTagOfPage(StandardUnitTagPO po,Pagination page);

	public List<StandardUnitTagPO> findStandardUnitTagAll(StandardUnitTagPO po);
	/**
	 * 根据suId查询su标签信息
	 * @param suId
	 * @return
	 */
	public List<String> findStandardUnitTagBySuId(Long suId);
}
	