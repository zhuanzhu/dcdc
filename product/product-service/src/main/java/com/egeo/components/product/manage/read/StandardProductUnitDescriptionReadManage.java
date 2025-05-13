package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitDescriptionReadManage {

	public StandardProductUnitDescriptionPO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionPO po);

	public PageResult<StandardProductUnitDescriptionPO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionPO po,Pagination page);

	public List<StandardProductUnitDescriptionPO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionPO po);
}
	