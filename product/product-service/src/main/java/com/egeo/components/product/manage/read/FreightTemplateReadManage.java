package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FreightTemplateReadManage {

	public FreightTemplatePO findFreightTemplateById(FreightTemplatePO po);

	public PageResult<FreightTemplatePO> findFreightTemplateOfPage(FreightTemplatePO po,Pagination page);

	public List<FreightTemplatePO> findFreightTemplateAll(FreightTemplatePO po);
}
	