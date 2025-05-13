package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.orm.BaseWriteDAO;

public interface FreightTemplateWriteDAO extends BaseWriteDAO<FreightTemplatePO> {
	/**
	 * 根据商家id停用运费模版
	 * @param merchantId
	 * @return
	 */
	int stopDreightTemplateByMerchantId(
			@Param("merchantId")Long merchantId,
			@Param("storeId")Long storeId);
}
	