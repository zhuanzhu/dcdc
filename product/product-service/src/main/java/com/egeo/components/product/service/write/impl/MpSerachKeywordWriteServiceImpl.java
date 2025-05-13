package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MpSerachKeywordWriteService;
import com.egeo.components.product.manage.write.MpSerachKeywordWriteManage;
import com.egeo.components.product.converter.MpSerachKeywordConverter;
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.components.product.po.MpSerachKeywordPO;

@Service("mpSerachKeywordWriteService")
public class MpSerachKeywordWriteServiceImpl  implements MpSerachKeywordWriteService {
	@Autowired
	private MpSerachKeywordWriteManage mpSerachKeywordWriteManage;

	@Override
	public Long insertMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
		Long rt = mpSerachKeywordWriteManage.insertMpSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int updateMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
		int rt = mpSerachKeywordWriteManage.updateMpSerachKeywordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
		int rt = mpSerachKeywordWriteManage.deleteMpSerachKeywordWithTx(po);		
		return rt;
	}
}
	