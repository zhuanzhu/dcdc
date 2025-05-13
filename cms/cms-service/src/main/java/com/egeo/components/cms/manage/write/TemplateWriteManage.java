package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.TemplatePO;


public interface TemplateWriteManage {

	Long insertTemplateWithTx(TemplatePO po);

	int updateTemplateWithTx(TemplatePO po);

	int deleteTemplateWithTx(TemplatePO po);

	/**
	 * 事务启用模板
	 *
     * @param platformId
     * @param templateId
     * @return
	 */
	boolean useTemplateWithTx(Long platformId, Long templateId, Integer clientType, Integer type, Integer companyType);

	/**
	 * 创建模板 并关联组件
	 * @param po
	 * @param eleIds
	 * @return
	 */
	Long createTemplateWithTx(TemplatePO po, List<Long> eleIds);

	/**
	 * 编辑模板,并关联组件
	 * @param po
	 * @param eleIds
	 * @return
	 */
	boolean editTemplateWithTx(TemplatePO po, List<Long> eleIds);
}
	