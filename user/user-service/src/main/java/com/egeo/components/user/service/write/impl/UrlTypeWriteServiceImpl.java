package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UrlTypeConverter;
import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.components.user.manage.write.UrlTypeWriteManage;
import com.egeo.components.user.po.UrlTypePO;
import com.egeo.components.user.service.write.UrlTypeWriteService;

@Service("urlTypeWriteService")
public class UrlTypeWriteServiceImpl implements UrlTypeWriteService {
	@Autowired
	private UrlTypeWriteManage urlTypeWriteManage;

	@Override
	public Long insertUrlTypeWithTx(UrlTypeDTO dto) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
		Long rt = urlTypeWriteManage.insertUrlTypeWithTx(po);		
		return rt;
	}

	@Override
	public int updateUrlTypeWithTx(UrlTypeDTO dto) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
		int rt = urlTypeWriteManage.updateUrlTypeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteUrlTypeWithTx(UrlTypeDTO dto) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
		int rt = urlTypeWriteManage.deleteUrlTypeWithTx(po);		
		return rt;
	}
}
	