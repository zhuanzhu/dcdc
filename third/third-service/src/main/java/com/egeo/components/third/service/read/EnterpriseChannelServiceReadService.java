package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceReadService {



    EnterpriseChannelServiceDTO findEnterpriseChannelServiceById(EnterpriseChannelServiceDTO dto);

    PageResult<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceOfPage(EnterpriseChannelServiceDTO dto, Pagination page);

    List<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceAll(EnterpriseChannelServiceDTO dto);

}
