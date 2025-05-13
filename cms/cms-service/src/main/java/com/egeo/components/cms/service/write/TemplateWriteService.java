package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.TemplateDTO;


public interface TemplateWriteService {

	public Long insertTemplateWithTx(TemplateDTO dto);

	public int updateTemplateWithTx(TemplateDTO dto);

	public int deleteTemplateWithTx(TemplateDTO dto);

	/**
	 * 启用模板
	 *
     * @param platformId
     * @param templateId
     * @param clientType
     * @param type
     * @return
	 */
	public boolean useTemplate(Long platformId, Long templateId, Integer clientType, Integer type, Integer companyType);

	/**
	 * 创建模板 并关联组件
	 * @param tmpl
	 * @param eleIds
	 * @return
	 */
	public Long createTemplate(TemplateDTO tmpl, List<Long> eleIds);

	/**
	 * 编辑模板,并关联组件
	 * @param tmpl
	 * @param eleIds
	 * @return
	 */
	public boolean editTemplate(TemplateDTO tmpl, List<Long> eleIds);
}
	