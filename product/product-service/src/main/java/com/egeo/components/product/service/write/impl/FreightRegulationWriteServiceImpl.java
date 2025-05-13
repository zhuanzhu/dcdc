package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.FreightRegulationWriteService;
import com.egeo.components.product.manage.write.FreightRegulationWriteManage;
import com.egeo.components.product.converter.FreightRegulationConverter;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.po.FreightRegulationPO;

@Service("freightRegulationWriteService")
public class FreightRegulationWriteServiceImpl  implements FreightRegulationWriteService {
	@Autowired
	private FreightRegulationWriteManage freightRegulationWriteManage;

	@Override
	public Long insertFreightRegulationWithTx(FreightRegulationDTO dto) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
		Long rt = freightRegulationWriteManage.insertFreightRegulationWithTx(po);		
		return rt;
	}

	@Override
	public int updateFreightRegulationWithTx(FreightRegulationDTO dto) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
		int rt = freightRegulationWriteManage.updateFreightRegulationWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFreightRegulationWithTx(FreightRegulationDTO dto) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
		int rt = freightRegulationWriteManage.deleteFreightRegulationWithTx(po);		
		return rt;
	}
}
	