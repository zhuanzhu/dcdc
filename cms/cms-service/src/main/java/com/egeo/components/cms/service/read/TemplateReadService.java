package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface TemplateReadService {

	public TemplateDTO findTemplateById(Long id);

	public PageResult<TemplateDTO> findTemplateOfPage(TemplateDTO dto,Pagination page);

	public List<TemplateDTO> findTemplateAll(TemplateDTO dto);

	/**
	 * 查询模板分页列表
	 * @param name
	 * @param page
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	public PageResult<TemplateDTO> queryTemplatePage(
			String name,Integer type , Pagination page, Integer status, Integer clientType,Integer companyType,Long platformId);

	/**
	 * 根据客户端类型查询被启用的模板
	 * @param clientType
	 * @return
	 */
	public TemplateDTO queryInUseTemplateByClientType(Integer clientType,Integer type, Integer companyType,Long platformId);
}
	