package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UrlConverter;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.manage.write.UrlWriteManage;
import com.egeo.components.user.po.UrlPO;
import com.egeo.components.user.service.write.UrlWriteService;


@Service("urlWriteService")
public class UrlWriteServiceImpl implements UrlWriteService {
	@Autowired
	private UrlWriteManage urlWriteManage;
	
	@Override
	public int saveOrUpdateWithTx(UrlDTO dto) {
		int poId;
		
		if(dto.getId() != null){
			poId = urlWriteManage.UpdateWithTx(UrlConverter.toPO(dto));
		}else{
			poId = urlWriteManage.saveWithTx(UrlConverter.toPO(dto));
		}
		return poId;
	}

	@Override
	public void deleteWithTx(UrlDTO dto) {
		UrlPO po = UrlConverter.toPO(dto);
		urlWriteManage.delete(po);
	}

}
	