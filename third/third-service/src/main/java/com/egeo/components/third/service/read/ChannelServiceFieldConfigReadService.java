package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/6/20 10:52
 * @Version V1.0
 **/
public interface ChannelServiceFieldConfigReadService {

    ChannelServiceFieldConfigDTO findChannelServiceFieldConfigById(ChannelServiceFieldConfigDTO dto);

    PageResult<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigOfPage(ChannelServiceFieldConfigDTO dto, Pagination page);

    List<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigAll(ChannelServiceFieldConfigDTO dto);

}
