package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.ProvCityAreaConverter;
import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.components.config.manage.write.ProvCityAreaWriteManage;
import com.egeo.components.config.po.ProvCityAreaPO;
import com.egeo.components.config.service.write.ProvCityAreaWriteService;

@Service("provCityAreaWriteService")
public class ProvCityAreaWriteServiceImpl implements ProvCityAreaWriteService {
	@Autowired
	private ProvCityAreaWriteManage provCityAreaWriteManage;

	@Override
	public Long insertProvCityAreaWithTx(ProvCityAreaDTO dto) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
		Long rt = provCityAreaWriteManage.insertProvCityAreaWithTx(po);		
		return rt;
	}

	@Override
	public int updateProvCityAreaWithTx(ProvCityAreaDTO dto) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
		int rt = provCityAreaWriteManage.updateProvCityAreaWithTx(po);		
		return rt;
	}

	@Override
	public int deleteProvCityAreaWithTx(ProvCityAreaDTO dto) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
		int rt = provCityAreaWriteManage.deleteProvCityAreaWithTx(po);		
		return rt;
	}
}
	