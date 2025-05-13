package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.InfoTemplateParameterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateParameterReadManage {

	public InfoTemplateParameterPO findInfoTemplateParameterById(InfoTemplateParameterPO po);

	public PageResult<InfoTemplateParameterPO> findInfoTemplateParameterOfPage(InfoTemplateParameterPO po,Pagination page);

	public List<InfoTemplateParameterPO> findInfoTemplateParameterAll(InfoTemplateParameterPO po);
}
	