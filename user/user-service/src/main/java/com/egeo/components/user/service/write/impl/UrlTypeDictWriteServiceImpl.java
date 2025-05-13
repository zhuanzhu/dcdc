package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UrlTypeDictConverter;
import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.components.user.manage.write.UrlTypeDictWriteManage;
import com.egeo.components.user.po.UrlTypeDictPO;
import com.egeo.components.user.service.write.UrlTypeDictWriteService;

@Service("urlTypeDictWriteService")
public class UrlTypeDictWriteServiceImpl implements UrlTypeDictWriteService {
	@Autowired
	private UrlTypeDictWriteManage urlTypeDictWriteManage;

	@Override
	public Long insertUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
		Long rt = urlTypeDictWriteManage.insertUrlTypeDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
		int rt = urlTypeDictWriteManage.updateUrlTypeDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
		int rt = urlTypeDictWriteManage.deleteUrlTypeDictWithTx(po);		
		return rt;
	}
}
	