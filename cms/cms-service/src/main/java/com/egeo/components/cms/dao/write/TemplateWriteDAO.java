package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.TemplatePO;
import com.egeo.orm.BaseWriteDAO;

public interface TemplateWriteDAO extends BaseWriteDAO<TemplatePO> {

	/**
	 * 停用某客户端类型下的所有某类型模板
	 * (例如停用app端所有商城模板)
	 * @param templateId
	 * @param platformId
     * @return
	 */
	int disableTmplsByClientTypeAndType(@Param("platformId") Long platformId, @Param("clientType") Integer clientType, @Param("type") Integer type,
                                        @Param("companyType") Integer companyType);

	/**
	 * 启用指定模板
	 * @param templateId
	 * @return
	 */
	int useTemplate(@Param("id")Long templateId);
}
	