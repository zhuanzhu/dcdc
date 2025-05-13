package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ClientPayTypeConfigReadService {

	public ClientPayTypeConfigDTO findClientPayTypeConfigById(ClientPayTypeConfigDTO dto);

	public PageResult<ClientPayTypeConfigDTO> findClientPayTypeConfigOfPage(ClientPayTypeConfigDTO dto,Pagination page);

	public List<ClientPayTypeConfigDTO> findClientPayTypeConfigAll(ClientPayTypeConfigDTO dto);
}
	