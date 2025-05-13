package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;


public interface FreightTemplateWriteService {
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	public Long insertFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList);
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	public int updateFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList);

	public int deleteFreightTemplateWithTx(FreightTemplateDTO dto);
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	public int startFreightTemplateWithTx(Long freightTemplateId);
}
	