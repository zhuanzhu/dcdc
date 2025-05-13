package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ImportTemplateDictPO;
import com.egeo.orm.BaseReadDAO;

public interface ImportTemplateDictReadDAO extends BaseReadDAO<ImportTemplateDictPO>{
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	String findImportTemplateDictUrlByType(@Param("type")Integer type, @Param("platformId")Long platformId);
}
