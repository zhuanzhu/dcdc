package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.LocalParamWriteService;
import com.egeo.components.cms.manage.write.LocalParamWriteManage;
import com.egeo.components.cms.converter.LocalParamConverter;
import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.components.cms.po.LocalParamPO;

@Service("localParamWriteService")
public class LocalParamWriteServiceImpl  implements LocalParamWriteService {
	@Autowired
	private LocalParamWriteManage localParamWriteManage;

	@Override
	public Long insertLocalParamWithTx(LocalParamDTO dto) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
		Long rt = localParamWriteManage.insertLocalParamWithTx(po);		
		return rt;
	}

	@Override
	public int updateLocalParamWithTx(LocalParamDTO dto) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
		int rt = localParamWriteManage.updateLocalParamWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLocalParamWithTx(LocalParamDTO dto) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
		int rt = localParamWriteManage.deleteLocalParamWithTx(po);		
		return rt;
	}
}
	