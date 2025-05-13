package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ClientPayTypeConfigConverter;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.manage.write.ClientPayTypeConfigWriteManage;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.components.user.service.write.ClientPayTypeConfigWriteService;

@Service("clientPayTypeConfigWriteService")
public class ClientPayTypeConfigWriteServiceImpl implements ClientPayTypeConfigWriteService {
	@Autowired
	private ClientPayTypeConfigWriteManage clientPayTypeConfigWriteManage;

	@Override
	public Long insertClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		Long rt = clientPayTypeConfigWriteManage.insertClientPayTypeConfigWithTx(po);		
		return rt;
	}

	@Override
	public int updateClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		int rt = clientPayTypeConfigWriteManage.updateClientPayTypeConfigWithTx(po);		
		return rt;
	}

	@Override
	public int deleteClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		int rt = clientPayTypeConfigWriteManage.deleteClientPayTypeConfigWithTx(po);		
		return rt;
	}
}
	