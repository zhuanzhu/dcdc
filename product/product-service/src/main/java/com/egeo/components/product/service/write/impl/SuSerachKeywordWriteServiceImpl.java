package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SuSerachKeywordWriteService;
import com.egeo.components.product.manage.write.SuSerachKeywordWriteManage;
import com.egeo.components.product.converter.SuSerachKeywordConverter;
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.components.product.po.SuSerachKeywordPO;

@Service("suSerachKeywordWriteService")
public class SuSerachKeywordWriteServiceImpl  implements SuSerachKeywordWriteService {
	@Autowired
	private SuSerachKeywordWriteManage suSerachKeywordWriteManage;

	@Override
	public Long insertSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
		Long rt = suSerachKeywordWriteManage.insertSuSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int updateSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
		int rt = suSerachKeywordWriteManage.updateSuSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
		int rt = suSerachKeywordWriteManage.deleteSuSerachKeywordWithTx(po);		
		return rt;
	}
}
	