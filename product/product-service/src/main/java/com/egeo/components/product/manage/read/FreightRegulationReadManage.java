package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FreightRegulationReadManage {

	public FreightRegulationPO findFreightRegulationById(FreightRegulationPO po);

	public PageResult<FreightRegulationPO> findFreightRegulationOfPage(FreightRegulationPO po,Pagination page);

	public List<FreightRegulationPO> findFreightRegulationAll(FreightRegulationPO po);
}
	