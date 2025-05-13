package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.orm.BaseReadDAO;

public interface FreightTemplateReadDAO extends BaseReadDAO<FreightTemplatePO>{
	/**
	 * 根据运费模版Id停用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	int stopFreightTemplateWithTx(@Param("freightTemplateId")Long freightTemplateId);
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 */
	void startFreightTemplateWithTx(@Param("freightTemplateId")Long freightTemplateId);
}
	