package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.service.read.ClientReadService;
import com.egeo.components.user.service.write.ClientWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ClientFacade {
	
	@Resource
	private ClientReadService clientReadService;
	
	@Resource
	private ClientWriteService clientWriteService;
	
	
	public ClientDTO findClientById(ClientDTO dto){
		
		return clientReadService.findClientById(dto);
	}

	public PageResult<ClientDTO> findClientOfPage(ClientDTO dto,Pagination page){
		
		return clientReadService.findClientOfPage(dto, page);
		
	}

	public List<ClientDTO> findClientAll(ClientDTO dto){
		
		return clientReadService.findClientAll(dto);
		
	}

	public Long insertClientWithTx(ClientDTO dto){
		
		return clientWriteService.insertClientWithTx(dto);
	}

	public int updateClientWithTx(ClientDTO dto){
		
		return clientWriteService.updateClientWithTx(dto);
	}

	public int deleteClientWithTx(ClientDTO dto){
		
		return clientWriteService.deleteClientWithTx(dto);
		
	}

}
	