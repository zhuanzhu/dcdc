package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;

import java.util.List;


public interface ClientWriteService {

	public Long insertClientWithTx(ClientDTO dto);

	public int updateClientWithTx(ClientDTO dto);

	public int deleteClientWithTx(ClientDTO dto);
	public boolean updateClientPayTypeWithTx(ClientDTO clientDTO, List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList);
}
	