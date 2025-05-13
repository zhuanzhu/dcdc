package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ClientManage {

	public ClientDTO findClientById(ClientDTO dto);	

	public PageResult<ClientDTO> findClientOfPage(ClientDTO dto,Pagination page);

	public List<ClientDTO> findClientAll(ClientDTO dto);

	Long insertClientWithTx(ClientDTO dto);

	int updateClientWithTx(ClientDTO dto);

	int deleteClientWithTx(ClientDTO dto);
}
	