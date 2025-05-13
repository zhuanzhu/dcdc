package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ClientReadService {

	public ClientDTO findClientById(ClientDTO dto);

	public PageResult<ClientDTO> findClientOfPage(ClientDTO dto,Pagination page);

	public List<ClientDTO> findClientAll(ClientDTO dto);
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	public List<ClientDTO> findClientByClientIds(List<Long> clientIds);
	/**
	 * 根据运营方Id集合查询运营方名称
	 * @param clientIdList
	 * @return
	 */
	public List<String> clientNameByClientIds(List<Long> clientIdList);
}
	