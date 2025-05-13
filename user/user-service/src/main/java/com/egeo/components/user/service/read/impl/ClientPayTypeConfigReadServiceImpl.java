package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ClientPayTypeConfigConverter;
import com.egeo.components.user.dao.read.ClientPayTypeConfigReadDAO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.components.user.service.read.ClientPayTypeConfigReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("clientPayTypeConfigReadService")
public class ClientPayTypeConfigReadServiceImpl implements ClientPayTypeConfigReadService {
	@Autowired
	private ClientPayTypeConfigReadDAO clientPayTypeConfigReadDAO;

	@Override
	public ClientPayTypeConfigDTO findClientPayTypeConfigById(ClientPayTypeConfigDTO dto) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		ClientPayTypeConfigPO clientPayTypeConfigpo = new ClientPayTypeConfigPO();
		clientPayTypeConfigpo.setId(po.getId());		
		ClientPayTypeConfigPO list = clientPayTypeConfigReadDAO.findById(clientPayTypeConfigpo);		
		return ClientPayTypeConfigConverter.toDTO(list);
	}

	@Override
	public PageResult<ClientPayTypeConfigDTO> findClientPayTypeConfigOfPage(ClientPayTypeConfigDTO dto, Pagination page) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		PageResult<ClientPayTypeConfigPO> pageResult = new PageResult<ClientPayTypeConfigPO>();
		List<ClientPayTypeConfigPO> listT = null;

		int cnt = clientPayTypeConfigReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = clientPayTypeConfigReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<ClientPayTypeConfigPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<ClientPayTypeConfigDTO> list = ClientPayTypeConfigConverter.toDTO(pageResult.getList());
        PageResult<ClientPayTypeConfigDTO> result = new PageResult<ClientPayTypeConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ClientPayTypeConfigDTO> findClientPayTypeConfigAll(ClientPayTypeConfigDTO dto) {
		ClientPayTypeConfigPO po = ClientPayTypeConfigConverter.toPO(dto);
		List<ClientPayTypeConfigPO> list = clientPayTypeConfigReadDAO.findAll(po,null);		
		return ClientPayTypeConfigConverter.toDTO(list);
	}
}
	