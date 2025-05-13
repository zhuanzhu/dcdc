package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceChoiceReadService {


    EnterpriseChannelServiceChoiceDTO findEnterpriseChannelServiceChoiceById(EnterpriseChannelServiceChoiceDTO dto);

    PageResult<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceOfPage(EnterpriseChannelServiceChoiceDTO dto, Pagination page);

    List<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceAll(EnterpriseChannelServiceChoiceDTO dto);

}
