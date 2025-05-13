package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SkuAttValueReadManage {

	public SkuAttValuePO findSkuAttValueById(SkuAttValuePO po);

	public PageResult<SkuAttValuePO> findSkuAttValueOfPage(SkuAttValuePO po,Pagination page);

	public List<SkuAttValuePO> findSkuAttValueAll(SkuAttValuePO po);
}
	