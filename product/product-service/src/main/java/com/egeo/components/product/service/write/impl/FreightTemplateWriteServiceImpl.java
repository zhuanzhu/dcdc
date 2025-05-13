package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.FreightTemplateWriteService;
import com.egeo.components.product.manage.write.FreightTemplateWriteManage;
import com.egeo.components.product.converter.FreightRegulationConverter;
import com.egeo.components.product.converter.FreightTemplateConverter;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.po.FreightTemplatePO;


@Service("freightTemplateWriteService")
public class FreightTemplateWriteServiceImpl  implements FreightTemplateWriteService {
	@Autowired
	private FreightTemplateWriteManage freightTemplateWriteManage;
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	@Override
	public Long insertFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
		Long rt = freightTemplateWriteManage.insertFreightTemplateWithTx(po,FreightRegulationConverter.toPO(freightRegulationList));		
		return rt;
	}
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	@Override
	public int updateFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
		int rt = freightTemplateWriteManage.updateFreightTemplateWithTx(po,FreightRegulationConverter.toPO(freightRegulationList));		
		return rt;
	}

	@Override
	public int deleteFreightTemplateWithTx(FreightTemplateDTO dto) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
		int rt = freightTemplateWriteManage.deleteFreightTemplateWithTx(po);		
		return rt;
	}
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	@Override
	public int startFreightTemplateWithTx(Long freightTemplateId) {
		// TODO Auto-generated method stub
		return freightTemplateWriteManage.startFreightTemplateWithTx(freightTemplateId);
	}
}
	