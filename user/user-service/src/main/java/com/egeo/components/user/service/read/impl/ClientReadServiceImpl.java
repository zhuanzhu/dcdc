package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ClientConverter;
import com.egeo.components.user.dao.read.ClientReadDAO;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.po.ClientPO;
import com.egeo.components.user.service.read.ClientReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("clientReadService")
public class ClientReadServiceImpl implements ClientReadService {
	@Autowired
	private ClientReadDAO clientReadDAO;

	@Override
	public ClientDTO findClientById(ClientDTO dto) {
		ClientPO po = ClientConverter.toPO(dto);
		ClientPO clientpo = new ClientPO();
		clientpo.setId(po.getId());
		ClientPO list = clientReadDAO.findById(clientpo);	
		return ClientConverter.toDTO(list);
	}

	@Override
	public PageResult<ClientDTO> findClientOfPage(ClientDTO dto, Pagination page) {
		ClientPO po = ClientConverter.toPO(dto);
        PageResult<ClientPO> pageResult = new PageResult<ClientPO>();
		List<ClientPO> listT = null;

		int cnt = clientReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = clientReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<ClientPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<ClientDTO> list = ClientConverter.toDTO(pageResult.getList());
        PageResult<ClientDTO> result = new PageResult<ClientDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ClientDTO> findClientAll(ClientDTO dto) {
		ClientPO po = ClientConverter.toPO(dto);
		List<ClientPO> list = clientReadDAO.findAll(po,null);		
		return ClientConverter.toDTO(list);
	}
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	@Override
	public List<ClientDTO> findClientByClientIds(List<Long> clientIds) {
		List<ClientPO> list = clientReadDAO.findClientByClientIds(clientIds);
		return ClientConverter.toDTO(list);
	}
	/**
	 * 根据运营方Id集合查询运营方名称
	 * @param clientIdList
	 * @return
	 */
	@Override
	public List<String> clientNameByClientIds(List<Long> clientIdList) {
		// TODO Auto-generated method stub
		return clientReadDAO.clientNameByClientIds(clientIdList);
	}
}
	