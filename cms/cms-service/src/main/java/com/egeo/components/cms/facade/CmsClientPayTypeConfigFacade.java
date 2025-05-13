package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.ClientPayTypeConfigClient;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;

/**
 * Created by 0.0 on 2018/8/30.
 */
@Component("cmsClientPayTypeConfigFacade")
public class CmsClientPayTypeConfigFacade {
	@Autowired
    private ClientPayTypeConfigClient clientPayTypeConfigReadService;

    public List<ClientPayTypeConfigDTO> getClientPayTypeByClientIdAndIsStop(ClientPayTypeConfigDTO dto) {
        return clientPayTypeConfigReadService.findClientPayTypeConfigAll(dto);

    }
}
