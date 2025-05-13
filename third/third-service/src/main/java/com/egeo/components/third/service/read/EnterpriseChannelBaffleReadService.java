package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelBaffleReadService {

    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffleById(EnterpriseChannelBaffleDTO dto);

    public PageResult<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleOfPage(EnterpriseChannelBaffleDTO dto, Pagination page);

    public List<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleAll(EnterpriseChannelBaffleDTO dto);
}
