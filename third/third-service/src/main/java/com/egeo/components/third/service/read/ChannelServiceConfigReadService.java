package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelServiceConfigReadService {

    ChannelServiceConfigDTO findChannelServiceConfigById(ChannelServiceConfigDTO dto);

    PageResult<ChannelServiceConfigDTO> findChannelServiceConfigOfPage(ChannelServiceConfigDTO dto, Pagination page);

    List<ChannelServiceConfigDTO> findChannelServiceConfigAll(ChannelServiceConfigDTO dto);

}
