package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:20
 * @Version V1.0
 **/
public interface ChannelLogReadService {

    ChannelLogDTO findChannelLogById(ChannelLogDTO dto);

    PageResult<ChannelLogDTO> findChannelLogOfPage(ChannelLogDTO dto, Pagination page);

    List<ChannelLogDTO> findChannelLogAll(ChannelLogDTO dto);
}
