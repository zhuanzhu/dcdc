package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.ClientPayTypeConfigDTO;


public interface ClientPayTypeConfigWriteService {

	public Long insertClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto);

	public int updateClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto);

	public int deleteClientPayTypeConfigWithTx(ClientPayTypeConfigDTO dto);
}
	