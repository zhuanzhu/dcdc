package com.egeo.components.user.fo;

import java.util.List;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;

public class UpdateClientPayTypeWithTxFO {
	private ClientDTO clientDTO;
	private List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList;
	public ClientDTO getClientDTO() {
		return clientDTO;
	}
	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}
	public List<ClientPayTypeConfigDTO> getClientPayTypeConfigDTOList() {
		return clientPayTypeConfigDTOList;
	}
	public void setClientPayTypeConfigDTOList(List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList) {
		this.clientPayTypeConfigDTOList = clientPayTypeConfigDTOList;
	}
	
}
