package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.SuListWriteService;
import com.egeo.components.cms.manage.write.SuListWriteManage;
import com.egeo.components.cms.converter.SuListConverter;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.po.SuListPO;

@Service("suListWriteService")
public class SuListWriteServiceImpl  implements SuListWriteService {
	@Autowired
	private SuListWriteManage suListWriteManage;

	@Override
	public Long insertSuListWithTx(SuListDTO dto) {
		SuListPO po = SuListConverter.toPO(dto);
		Long rt = suListWriteManage.insertSuListWithTx(po);		
		return rt;
	}

	@Override
	public int updateSuListWithTx(SuListDTO dto) {
		SuListPO po = SuListConverter.toPO(dto);
		int rt = suListWriteManage.updateSuListWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSuListWithTx(SuListDTO dto) {
		SuListPO po = SuListConverter.toPO(dto);
		int rt = suListWriteManage.deleteSuListWithTx(po);		
		return rt;
	}
}
	