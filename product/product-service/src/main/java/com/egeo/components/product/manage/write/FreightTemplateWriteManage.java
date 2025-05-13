package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.components.product.po.FreightTemplatePO;


public interface FreightTemplateWriteManage {
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	Long insertFreightTemplateWithTx(FreightTemplatePO po,List<FreightRegulationPO> freightRegulationList);
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	int updateFreightTemplateWithTx(FreightTemplatePO po,List<FreightRegulationPO> freightRegulationList);

	int deleteFreightTemplateWithTx(FreightTemplatePO po);
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	int startFreightTemplateWithTx(Long freightTemplateId);
}
	