package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ClientConverter;
import com.egeo.components.user.converter.ClientPayTypeConfigConverter;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.manage.write.ClientWriteManage;
import com.egeo.components.user.po.ClientPO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.components.user.service.write.ClientWriteService;

@Service("clientWriteService")
public class ClientWriteServiceImpl implements ClientWriteService {
	@Autowired
	private ClientWriteManage clientWriteManage;

	@Override
	public Long insertClientWithTx(ClientDTO dto) {
		ClientPO po = ClientConverter.toPO(dto);
		Long rt = clientWriteManage.insertClientWithTx(po);		
		return rt;
	}

	@Override
	public int updateClientWithTx(ClientDTO dto) {
		ClientPO po = ClientConverter.toPO(dto);
		int rt = clientWriteManage.updateClientWithTx(po);		
		return rt;
	}

	@Override
	public int deleteClientWithTx(ClientDTO dto) {
		ClientPO po = ClientConverter.toPO(dto);
		int rt = clientWriteManage.deleteClientWithTx(po);		
		return rt;
	}

	@Override
	public boolean updateClientPayTypeWithTx(ClientDTO clientDTO, List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList) {
		ClientPO clientPO=ClientConverter.toPO(clientDTO);
		List<ClientPayTypeConfigPO> clientPayTypeConfigPOList = ClientPayTypeConfigConverter.toPO(clientPayTypeConfigDTOList);
		return clientWriteManage.updateClientPayTypeWithTx(clientPO,clientPayTypeConfigPOList);
	}
}
	