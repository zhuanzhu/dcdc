package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.fo.UpdateClientPayTypeWithTxFO;

/**
 * Created by 0.0 on 2018/8/30.
 */
@Component("cmsClientFacade")
public class CmsClientFacade {

	@Autowired
    private ClientClient clientReadService;
	@Autowired
    private ClientClient clientWriteService;

    public List<ClientDTO> getAllClientList(ClientDTO clientDTO) {
        List<ClientDTO> clientAll = clientReadService.findClientAll(clientDTO);
        return clientAll;

    }
    public ClientDTO getClientById(ClientDTO clientDTO){
        ClientDTO clientById = clientReadService.findClientById(clientDTO);
        return clientById;
    }

    public boolean updateClientPayTypeByClientId(ClientDTO clientDTO, List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList) {
    	UpdateClientPayTypeWithTxFO fo = new UpdateClientPayTypeWithTxFO();
    	fo.setClientDTO(clientDTO);
    	fo.setClientPayTypeConfigDTOList(clientPayTypeConfigDTOList);
        return clientWriteService.updateClientPayTypeWithTx(fo);

    }
}
