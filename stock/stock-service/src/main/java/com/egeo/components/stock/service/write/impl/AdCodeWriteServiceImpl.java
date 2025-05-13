package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.AdCodeWriteService;
import com.egeo.components.stock.manage.write.AdCodeWriteManage;
import com.egeo.components.stock.converter.AdCodeConverter;
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.components.stock.po.AdCodePO;

@Service("adCodeWriteService")
public class AdCodeWriteServiceImpl  implements AdCodeWriteService {
	@Autowired
	private AdCodeWriteManage adCodeWriteManage;

	@Override
	public Long insertAdCodeWithTx(AdCodeDTO dto) {
		AdCodePO po = AdCodeConverter.toPO(dto);
		Long rt = adCodeWriteManage.insertAdCodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateAdCodeWithTx(AdCodeDTO dto) {
		AdCodePO po = AdCodeConverter.toPO(dto);
		int rt = adCodeWriteManage.updateAdCodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAdCodeWithTx(AdCodeDTO dto) {
		AdCodePO po = AdCodeConverter.toPO(dto);
		int rt = adCodeWriteManage.deleteAdCodeWithTx(po);		
		return rt;
	}
}
	