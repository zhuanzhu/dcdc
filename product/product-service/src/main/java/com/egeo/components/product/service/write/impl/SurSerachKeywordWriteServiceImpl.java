package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SurSerachKeywordWriteService;
import com.egeo.components.product.manage.write.SurSerachKeywordWriteManage;
import com.egeo.components.product.converter.SurSerachKeywordConverter;
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.components.product.po.SurSerachKeywordPO;

@Service("surSerachKeywordWriteService")
public class SurSerachKeywordWriteServiceImpl  implements SurSerachKeywordWriteService {
	@Autowired
	private SurSerachKeywordWriteManage surSerachKeywordWriteManage;

	@Override
	public Long insertSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
		Long rt = surSerachKeywordWriteManage.insertSurSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int updateSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
		int rt = surSerachKeywordWriteManage.updateSurSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
		int rt = surSerachKeywordWriteManage.deleteSurSerachKeywordWithTx(po);		
		return rt;
	}
}
	