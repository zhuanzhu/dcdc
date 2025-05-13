package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.channel.ChannelProductTextDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductTextReadService {

    public ChannelProductTextDTO findChannelProductTextById(ChannelProductTextDTO dto);

    public PageResult<ChannelProductTextDTO> findChannelProductTextOfPage(ChannelProductTextDTO dto, Pagination page);

    public List<ChannelProductTextDTO> findChannelProductTextAll(ChannelProductTextDTO dto);

}
