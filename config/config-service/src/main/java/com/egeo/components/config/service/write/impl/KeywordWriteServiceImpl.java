package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.KeywordConverter;
import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.manage.write.KeywordWriteManage;
import com.egeo.components.config.po.KeywordPO;
import com.egeo.components.config.service.write.KeywordWriteService;

@Service("keywordWriteService")
public class KeywordWriteServiceImpl implements KeywordWriteService {
	@Autowired
	private KeywordWriteManage keywordWriteManage;

	@Override
	public Long insertKeywordWithTx(KeywordDTO dto) {
		KeywordPO po = KeywordConverter.toPO(dto);
		Long rt = keywordWriteManage.insertKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int updateKeywordWithTx(KeywordDTO dto) {
		KeywordPO po = KeywordConverter.toPO(dto);
		int rt = keywordWriteManage.updateKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteKeywordWithTx(KeywordDTO dto) {
		KeywordPO po = KeywordConverter.toPO(dto);
		int rt = keywordWriteManage.deleteKeywordWithTx(po);		
		return rt;
	}
}
	