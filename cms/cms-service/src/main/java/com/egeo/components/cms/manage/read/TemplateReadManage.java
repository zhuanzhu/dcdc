package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.TemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TemplateReadManage {

	public TemplatePO findTemplateById(TemplatePO po);

	public PageResult<TemplatePO> findTemplateOfPage(TemplatePO po,Pagination page);

	public List<TemplatePO> findTemplateAll(TemplatePO po);

	/**
	 * 查询模板分页列表
	 * @param name
	 * @param page
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	public PageResult<TemplatePO> queryTemplatePage(
			String name,Integer type , Pagination page, Integer status, Integer clientType,Integer companyType,Long platformId);

	/**
	 * 根据客户端类型查询被启用的模板
	 * @param clientType
	 * @return
	 */
	public TemplatePO queryInUseTemplateByClientType(Integer clientType,Integer type, Integer companyType,Long platformId);
}
	