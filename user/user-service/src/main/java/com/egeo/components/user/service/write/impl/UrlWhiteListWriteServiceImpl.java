package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UrlWhiteListConverter;
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.components.user.manage.write.UrlWhiteListWriteManage;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.components.user.service.write.UrlWhiteListWriteService;

@Service("urlWhiteListWriteService")
public class UrlWhiteListWriteServiceImpl implements UrlWhiteListWriteService {
	@Autowired
	private UrlWhiteListWriteManage urlWhiteListWriteManage;

	@Override
	public Long insertUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
		Long rt = urlWhiteListWriteManage.insertUrlWhiteListWithTx(po, platformIdList);		
		return rt;
	}

	@Override
	public int updateUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
		int rt = urlWhiteListWriteManage.updateUrlWhiteListWithTx(po, platformIdList);		
		return rt;
	}

	@Override
	public int deleteUrlWhiteListWithTx(UrlWhiteListDTO dto) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
		int rt = urlWhiteListWriteManage.deleteUrlWhiteListWithTx(po);		
		return rt;
	}
}
	