package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateReadManage {

	public InfoTemplatePO findInfoTemplateById(InfoTemplatePO po);

	public PageResult<InfoTemplatePO> findInfoTemplateOfPage(InfoTemplatePO po,Pagination page);

	public List<InfoTemplatePO> findInfoTemplateAll(InfoTemplatePO po);
}
	