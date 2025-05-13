package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.ClientManage;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.service.read.ClientReadService;
import com.egeo.components.user.service.write.ClientWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("client")
public class ClientManageImpl implements ClientManage{

	
	@Resource(name="clientReadService")
	private ClientReadService clientReadService;
	@Resource(name="clientWriteService")
	private ClientWriteService clientWriteService;

	@Override
	public ClientDTO findClientById(ClientDTO dto) {
		return clientReadService.findClientById(dto);
	}

	@Override
	public PageResult<ClientDTO> findClientOfPage(ClientDTO dto, Pagination page) {
		return clientReadService.findClientOfPage(dto, page);
	}

	@Override
	public List<ClientDTO> findClientAll(ClientDTO dto) {
		return clientReadService.findClientAll(dto);
	}

	@Override
	public Long insertClientWithTx(ClientDTO dto) {
		return clientWriteService.insertClientWithTx(dto);
	}

	@Override
	public int updateClientWithTx(ClientDTO dto) {
		return clientWriteService.updateClientWithTx(dto);
	}

	@Override
	public int deleteClientWithTx(ClientDTO dto) {
		return clientWriteService.deleteClientWithTx(dto);
	}


}
	