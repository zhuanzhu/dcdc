package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CommodityTemplatePO;
import com.egeo.orm.BaseReadDAO;

public interface CommodityTemplateReadDAO extends BaseReadDAO<CommodityTemplatePO>{
	/**
	 * 根据类型查询默认模版信息
	 * @param type
	 * @return
	 */
	CommodityTemplatePO findDefaultByType(
			@Param("type")Integer type,
			@Param("platformId")Long platformId);
}
	